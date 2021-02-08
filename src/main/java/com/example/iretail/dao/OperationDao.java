package com.example.iretail.dao;

import com.example.iretail.entity.Operation;

import java.util.List;

public interface OperationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);

    List<Operation> selectAll();

    int updateByCode(Operation record);

    List<Operation> selectByIdList(List<Integer> operationIdList);
}