package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Ccp;


public interface CcpServiceI {
	
    int deleteByPrimaryKey(String ccpId);

    int insert(Ccp record);

   // int insertSelective(Pc record);

    Ccp selectByPrimaryKey(String ccpId);

    int updateByPrimaryKeySelective(Ccp record);

    //int updateByPrimaryKey(Pc record);
    
    List<Ccp> getMyCcp(String maker); 
    
    Ccp selectCcpById(String ccpId);


}