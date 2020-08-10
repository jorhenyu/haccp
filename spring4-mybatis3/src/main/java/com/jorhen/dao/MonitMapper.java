package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Monit;
import com.jorhen.domain.Query;

public interface MonitMapper {
    int deleteByPrimaryKey(String monitId);

    int insert(Monit record);

    int insertSelective(Monit record);

    Monit selectByPrimaryKey(String monitId);

    int updateByPrimaryKeySelective(Monit record);

    int updateByPrimaryKey(Monit record);
    
    List<Monit> getMyMonit(@Param("rder") String rder);
    
    Monit selectMonitById(@Param("monitId") String monitId);
    
    List<Monit> getMyMonitByQuery(Query query);
}