package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Ha;


public interface HaServiceI {
	
    int deleteByPrimaryKey(String haId);

    int insert(Ha record);

   // int insertSelective(Pc record);

    Ha selectByPrimaryKey(String haId);

    int updateByPrimaryKeySelective(Ha record);

    //int updateByPrimaryKey(Pc record);
    
    List<Ha> getMyHa(String maker); 
    
    Ha selectHaById(String haId);
    
    List<Ha> selectHaByPlanId(String planId);
    
    List<Ha> getHaByPlanIdDistinct(String rder);


}