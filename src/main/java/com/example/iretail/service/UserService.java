package com.example.iretail.service;

import com.example.iretail.dao.*;
import com.example.iretail.entity.*;
import com.example.iretail.dto.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService {
    @Resource
    private OperationDao operationDao;
    @Resource
    private OrgDao orgDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleOperationDao roleOperationDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserOrgDao userOrgDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private StoreDao storeDao;

    public UserInfo login(Map<String, String> userMap) {
        User user = userDao.selectByUsername(userMap.get("username"));
        UserInfo userInfo = new UserInfo();
        List<Integer> roleIdList = userRoleDao.selectRoleIdByUserId(user.getId());
        List<Role> roleList = roleDao.selectByIdList(roleIdList);
        List<Integer> orgIdList = userOrgDao.selectOrgIdByUserId(user.getId());
        List<Org> orgList = orgDao.selectByIdList(orgIdList);
        Set<Org> checkedSet = new HashSet<>();
        List<Org> orgRecurList= buildRecurOrgList(orgList,checkedSet);
        List<Integer> operationIdList = roleOperationDao.selectOperationIdByRoleIdList(roleIdList);
        List<Operation> operationList = operationDao.selectByIdList(operationIdList);
        List<Store> storeList = storeDao.selectAllByOrgIdList(orgRecurList);
        userInfo.setRoleList(roleList);
        userInfo.setStoreList(storeList);
        userInfo.setOrgList(orgRecurList);
        userInfo.setUserId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setOperationList(operationList);
        return userInfo;
    }

    private List<Org> buildRecurOrgList(List<Org> searchList, Set<Org> checkedSet) {
        List<Org> resultList = new ArrayList<>(searchList);
        while (searchList.size() > 0) {
            searchList.removeAll(checkedSet);
            List<Org> childList = orgDao.selectChildList(searchList);
            checkedSet.addAll(searchList);
            searchList = childList;
            resultList.addAll(childList);
        }
        return resultList;
    }

    public Object register(Map<String, String> userMap) {
        return null;
    }
}
