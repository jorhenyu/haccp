package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Ps;


public interface PsServiceI {
	
    int deleteByPrimaryKey(String psId);

    int insert(Ps record);

   // int insertSelective(Ps record);

    Ps selectByPrimaryKey(String psId);

    int updateByPrimaryKeySelective(Ps record);

    //int updateByPrimaryKey(Ps record);
    
    List<Ps> getMyPs(String maker); 
    
    Ps selectPsById(String psId);


}