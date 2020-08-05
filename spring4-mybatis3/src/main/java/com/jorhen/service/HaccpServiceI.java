package com.jorhen.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Haccp;


public interface HaccpServiceI {
	
    int deleteByPrimaryKey(String haccpId);

    int insert(Haccp record);

   // int insertSelective(Pc record);

    Haccp selectByPrimaryKey(String haccpId);

    int updateByPrimaryKeySelective(Haccp record);

    //int updateByPrimaryKey(Pc record);
    
    List<Haccp> getMyHaccp(@Param("rder") String rder);
    
    Haccp selectHaccpById(@Param("haccpId") String haccpId);
    


}