package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ccp;

public interface CcpMapper {
    int deleteByPrimaryKey(String ccpId);

    int insert(Ccp record);

    int insertSelective(Ccp record);

    Ccp selectByPrimaryKey(String ccpId);

    int updateByPrimaryKeySelective(Ccp record);

    int updateByPrimaryKey(Ccp record);
    
    List<Ccp> getMyCcp(@Param("rder") String rder);
    
    Ccp selectCcpById(@Param("ccpId") String ccpId);
    
    List<Ccp> selectSHaByPname(@Param("pName") String pName, @Param("rder") String rder);
}