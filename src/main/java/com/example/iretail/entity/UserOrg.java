package com.example.iretail.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * user_org
 * @author 
 */
@ApiModel(value="com.example.iretail.entity.UserOrg")
@Data
public class UserOrg implements Serializable {
    private Integer id;

    private Integer orgId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}