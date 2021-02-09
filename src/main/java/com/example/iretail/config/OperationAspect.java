package com.example.iretail.config;

import com.example.iretail.dao.OperationDao;
import com.example.iretail.entity.Operation;
import com.example.iretail.utils.HttpContextUtil;
import com.example.iretail.utils.JwtUtil;
import com.example.iretail.dto.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class OperationAspect implements ApplicationRunner {
    private List<String> classNames = new ArrayList<>();
    private Map<String, String> operationMapNew = new HashMap<>();
    @Resource
    private JwtUtil jwtUtil;

    @Around(value = "@annotation(com.example.iretail.config.OperationAnno)")
    public Object aroundOperation(ProceedingJoinPoint point) throws Throwable {
        String jwt = HttpContextUtil.getHttpServletRequest().getHeader("Authorization");
        if (ObjectUtils.isEmpty(jwt)) {
            throw new BusinessException(ErrorCode.JWT_EMPTY, "Authorization in header require");
        }
        UserInfo userInfo = jwtUtil.verify(jwt);
        HttpContextUtil.getHttpServletRequest().getSession().setAttribute("userInfo",userInfo);
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        OperationAnno anno = method.getAnnotation(OperationAnno.class);
        for (Operation operation : userInfo.getOperationList()) {
            if (operation.getOperationCode().equals(anno.code())) {
                return point.proceed();
            }
        }
        throw new BusinessException(ErrorCode.OPERATION_DENY);
    }

    @Value("${controller.path}")
    private String controllerPath;

    @Value("${permission.update}")
    private Boolean update;

    /**
     * 每次启动项目将controller中新增权限同步到db
     * update=true开启
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        doScanPackage(controllerPath);
        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(RestController.class)) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    OperationAnno annotation = method.getAnnotation(OperationAnno.class);
                    if (annotation != null) {
                        operationMapNew.put(annotation.code(), annotation.desc());
                    }
                }
            }
        }
        if (update) {
            updateOperation();
        }
    }

    @Resource
    private OperationDao operationDao;

    /**
     * code相同desc不同:更新
     * code不存在:插入
     */
    private void updateOperation() {
        List<Operation> operationList = operationDao.selectAll();
        HashMap<String, String> operationMapDb = new HashMap<>();
        for (Operation operation : operationList) {
            operationMapDb.put(operation.getOperationCode(), operation.getOperationDesc());
        }
        operationMapNew.forEach((k, v) -> {
            String valueDb = operationMapDb.get(k);
            if (valueDb != null) {
                if (!valueDb.equals(v)) {
                    Operation operation = new Operation();
                    operation.setOperationCode(k);
                    operation.setOperationDesc(v);
                    operationDao.updateByCode(operation);
                }
            } else {
                Operation operation = new Operation();
                operation.setOperationCode(k);
                operation.setOperationDesc(v);
                operationDao.insert(operation);
            }
        });
    }

    private void doScanPackage(String packages) {
        URL url = this.getClass().getClassLoader().getResource("./" + packages.replaceAll("\\.", "/"));
        String fileStr = url.getFile();
        File files = new File(fileStr);


        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                doScanPackage(packages + "." + file.getName());
            } else {
                classNames.add(packages + "." + file.getName().replace(".class", ""));
            }
        }
    }
}
