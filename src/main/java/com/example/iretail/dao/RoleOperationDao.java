package com.example.iretail.dao;

import com.example.iretail.entity.RoleOperation;

import java.util.List;

public interface RoleOperationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleOperation record);

    int insertSelective(RoleOperation record);

    RoleOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleOperation record);

    int updateByPrimaryKey(RoleOperation record);

    List<Integer> selectOperationIdByRoleIdList(List<Integer> roleIdList);
}