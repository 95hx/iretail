package com.example.iretail.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * store
 * @author 
 */
@ApiModel(value="com.example.iretail.entity.Store")
@Data
public class Store implements Serializable {
    private Integer id;

    private String storeName;

    private Integer orgId;

    private static final long serialVersionUID = 1L;
}