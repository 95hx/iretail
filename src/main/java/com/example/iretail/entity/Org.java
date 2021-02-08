package com.example.iretail.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * org
 * @author 
 */
@ApiModel(value="com.example.iretail.entity.Org")
@Data
public class Org implements Serializable {
    private Integer id;

    private String orgName;

    private Integer level;

    private Integer parentId;

    private static final long serialVersionUID = 1L;
}