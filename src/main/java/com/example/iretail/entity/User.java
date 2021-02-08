package com.example.iretail.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private static final long serialVersionUID = 1L;
}