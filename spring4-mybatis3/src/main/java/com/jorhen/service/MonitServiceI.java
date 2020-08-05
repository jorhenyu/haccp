package com.jorhen.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Monit;


public interface MonitServiceI {
	
    int deleteByPrimaryKey(String monitId);

    int insert(Monit record);

   // int insertSelective(Pc record);

    Monit selectByPrimaryKey(String monitId);

    int updateByPrimaryKeySelective(Monit record);

    //int updateByPrimaryKey(Pc record);
    
    List<Monit> getMyMonit(@Param("rder") String rder);
    
    Monit selectMonitById(@Param("monitId") String monitId);


}