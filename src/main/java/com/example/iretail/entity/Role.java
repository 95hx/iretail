package com.example.iretail.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * role
 * @author 
 */
@ApiModel(value="com.example.iretail.entity.Role")
@Data
public class Role implements Serializable {
    private Integer id;
    private String roleName;
    private static final long serialVersionUID = 1L;
}