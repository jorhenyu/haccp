package com.jorhen.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Monit;
import com.jorhen.domain.Query;


public interface MonitServiceI {
	
    int deleteByPrimaryKey(String monitId);

    int insert(Monit record);

   // int insertSelective(Pc record);

    Monit selectByPrimaryKey(String monitId);

    int updateByPrimaryKeySelective(Monit record);

    //int updateByPrimaryKey(Pc record);
    
    List<Monit> getMyMonit(String rder);
    
    Monit selectMonitById(String monitId);
    
    List<Monit> getMyMonitByQuery(Query query);


}