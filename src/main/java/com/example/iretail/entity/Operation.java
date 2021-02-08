package com.example.iretail.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * operation
 * @author 
 */
@ApiModel(value="com.example.iretail.entity.Operation")
@Data
public class Operation implements Serializable {
    private Integer id;

    private String operationCode;

    private String operationDesc;

    private static final long serialVersionUID = 1L;
}