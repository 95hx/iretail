package com.example.iretail.utils;

import com.example.iretail.config.BusinessException;
import com.example.iretail.config.ErrorCode;
import com.example.iretail.entity.Org;
import com.example.iretail.entity.Store;
import com.example.iretail.dto.UserInfo;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserScopeUtil {
    public void checkOrg(Collection<Integer> orgIdList) {
        UserInfo userInfo = getUserInfo();
        Set<Integer> userOrgIdList = userInfo.getOrgList().stream().map(Org::getId).collect(Collectors.toSet());
        if (!userOrgIdList.containsAll(orgIdList)) {
            throw new BusinessException(ErrorCode.ORG_DENY, "org permission valid fail");
        }
    }

    public UserInfo getUserInfo() {
        return (UserInfo) HttpContextUtil.getHttpServletRequest().getSession().getAttribute("userInfo");
    }

    public void checkStore(Collection<Integer> storeIdList) {
        UserInfo userInfo = getUserInfo();
        Set<Integer> userStoreIdList = userInfo.getStoreList().stream().map(Store::getId).collect(Collectors.toSet());
        if (!userStoreIdList.containsAll(storeIdList)) {
            throw new BusinessException(ErrorCode.STORE_DENY, "store permission valid fail");
        }
    }
}
