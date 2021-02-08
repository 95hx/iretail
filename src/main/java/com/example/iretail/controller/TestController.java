package com.example.iretail.controller;

import com.example.iretail.config.OperationAnno;
import com.example.iretail.utils.UserScopeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private UserScopeUtil userScopeUtil;
    @RequestMapping("/1")
    @OperationAnno(desc = "test1权限update", code = "iretail.TestController.test1")
    public Object test1() {
        return "null";
    }

    @RequestMapping("/2")
    @OperationAnno(desc = "test1权限update", code = "iretail.TestController.test2")
    public Object test2() {
        ArrayList<Integer> orgIdList = new ArrayList<>();
        orgIdList.add(9);
        orgIdList.add(8);
        orgIdList.add(10);
        userScopeUtil.checkOrg(orgIdList);
        return "null";
    }
}
