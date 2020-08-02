package com.jorhen.dao;

import com.jorhen.domain.Ps;

public interface PsMapper {
    int deleteByPrimaryKey(String psId);

    int insert(Ps record);

    int insertSelective(Ps record);

    Ps selectByPrimaryKey(String psId);

    int updateByPrimaryKeySelective(Ps record);

    int updateByPrimaryKey(Ps record);
}