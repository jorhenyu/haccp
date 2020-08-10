package com.jorhen.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;


public interface PsServiceI {
	
    int deleteByPrimaryKey(String psId);

    int insert(Ps record);

   // int insertSelective(Ps record);

    Ps selectByPrimaryKey(String psId);

    int updateByPrimaryKeySelective(Ps record);

    //int updateByPrimaryKey(Ps record);
    
    List<Ps> getMyPs(String rder); 
    
    Ps selectPsById(String psId);
    
    Ps getMyPsBypLd(String planId);
    
    List<Ps> getMyPsByQuery(Query query); 


}