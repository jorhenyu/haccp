package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Pc;
import com.jorhen.domain.Query;


public interface PcServiceI {
	
    int deleteByPrimaryKey(String pcId);

    int insert(Pc record);

   // int insertSelective(Pc record);

    Pc selectByPrimaryKey(String pcId);

    int updateByPrimaryKeySelective(Pc record);

    //int updateByPrimaryKey(Pc record);
    
    List<Pc> getMyPc(String rder); 
    
    Pc selectPcById(String pcId);
    
    Pc getMyPcBypLd(String planId);
    
    List<Pc> getMyPcByQuery(Query query);


}