package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Pc;


public interface PcServiceI {
	
    int deleteByPrimaryKey(String pcId);

    int insert(Pc record);

   // int insertSelective(Pc record);

    Pc selectByPrimaryKey(String pcId);

    int updateByPrimaryKeySelective(Pc record);

    //int updateByPrimaryKey(Pc record);
    
    List<Pc> getMyPc(String maker); 
    
    Pc selectPcById(String pcId);


}