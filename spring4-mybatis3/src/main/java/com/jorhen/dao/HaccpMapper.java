package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ccp;
import com.jorhen.domain.Haccp;

public interface HaccpMapper {
    int deleteByPrimaryKey(String haccpId);

    int insert(Haccp record);

    int insertSelective(Haccp record);

    Haccp selectByPrimaryKey(String haccpId);

    int updateByPrimaryKeySelective(Haccp record);

    int updateByPrimaryKey(Haccp record);
    
    List<Haccp> getMyHaccp(@Param("rder") String rder);
    
    Haccp selectHaccpById(@Param("haccpId") String haccpId);
    
    List<Haccp> selectHaccpByPlanId(@Param("planId") String planId);
    
    List<Haccp> getHaccpByPlanIdDistinct(@Param("rder") String rder);
}