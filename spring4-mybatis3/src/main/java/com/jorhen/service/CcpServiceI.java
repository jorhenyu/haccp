package com.jorhen.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ccp;
import com.jorhen.domain.Query;
import com.jorhen.domain.Team;


public interface CcpServiceI {
	
    int deleteByPrimaryKey(String ccpId);

    int insert(Ccp record);

   // int insertSelective(Pc record);

    Ccp selectByPrimaryKey(String ccpId);

    int updateByPrimaryKeySelective(Ccp record);

    //int updateByPrimaryKey(Pc record);
    
    List<Ccp> getMyCcp(String maker); 
    
    Ccp selectCcpById(String ccpId);
    
    List<Ccp> selectSHaByPname(String pName, String rder);
    
    List<Ccp> selectCcpByPlanId(String planId);
    
    List<Ccp> getCcpByPlanIdDistinct(String rder);
    
    List<Ccp> getMyCcpByQuery(Query query); 


}