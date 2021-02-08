package com.example.iretail.dao;

import com.example.iretail.entity.UserOrg;

import java.util.List;

public interface UserOrgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrg record);

    int insertSelective(UserOrg record);

    UserOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrg record);

    int updateByPrimaryKey(UserOrg record);

    List<Integer> selectOrgIdByUserId(Integer id);
}