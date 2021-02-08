package com.example.iretail.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * user_role
 * @author 
 */
@Data
public class UserRole implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}