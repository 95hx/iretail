package com.example.iretail.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * role_operation
 * @author 
 */
@Data
public class RoleOperation implements Serializable {
    private Integer id;

    private Integer operationId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}