package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ha;
import com.jorhen.domain.Query;

public interface HaMapper {
    int deleteByPrimaryKey(String haId);

    int insert(Ha record);

    int insertSelective(Ha record);

    Ha selectByPrimaryKey(String haId);

    int updateByPrimaryKeySelective(Ha record);

    int updateByPrimaryKey(Ha record);
    List<Ha> getMyHa(@Param("rder") String rder);
    
    Ha selectHaById(@Param("haId") String haId);
    
    List<Ha> selectHaByPlanId(@Param("planId") String planId);
    
    List<Ha> getHaByPlanIdDistinct(@Param("rder") String rder);
    
    List<Ha> getMyHaByQuery(Query query);
}