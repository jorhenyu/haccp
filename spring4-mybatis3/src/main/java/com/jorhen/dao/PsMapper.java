package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ps;
import com.jorhen.domain.Team;

public interface PsMapper {
    int deleteByPrimaryKey(String psId);

    int insert(Ps record);

    int insertSelective(Ps record);

    Ps selectByPrimaryKey(String psId);

    int updateByPrimaryKeySelective(Ps record);

    int updateByPrimaryKey(Ps record);
    
    List<Ps> getMyPs(@Param("rder") String rder);
    
    Ps selectPsById(@Param("psId") String psId);
}