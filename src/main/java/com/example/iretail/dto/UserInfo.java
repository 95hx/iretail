package com.example.iretail.dto;

import com.example.iretail.entity.Operation;
import com.example.iretail.entity.Org;
import com.example.iretail.entity.Role;
import com.example.iretail.entity.Store;
import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private Integer userId;
    private String username;
    private List<Role> roleList;
    private List<Org> OrgList;
    private List<Store> storeList;
    private List<Operation> operationList;
}
