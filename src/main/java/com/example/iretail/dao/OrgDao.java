package com.example.iretail.dao;

import com.example.iretail.entity.Org;

import java.util.List;

public interface OrgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);

    List<Org> selectByIdList(List<Integer> orgIdList);

    List<Org> selectChildList(List<Org> orgList);
}