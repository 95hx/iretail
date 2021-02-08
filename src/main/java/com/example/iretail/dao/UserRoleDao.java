package com.example.iretail.dao;

import com.example.iretail.entity.UserRole;

import java.util.List;

public interface UserRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<Integer> selectRoleIdByUserId(Integer id);
}