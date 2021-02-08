package com.example.iretail.dao;

import com.example.iretail.entity.Role;

import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByIdList(List<Integer> roleIdList);
}