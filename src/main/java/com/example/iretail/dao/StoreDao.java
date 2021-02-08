package com.example.iretail.dao;

import com.example.iretail.entity.Org;
import com.example.iretail.entity.Store;

import java.util.Collection;
import java.util.List;

public interface StoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    List<Store> selectAllByOrgIdList(Collection<Org> orgList);
}