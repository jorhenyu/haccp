package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Ha;

public interface HaMapper {
    int deleteByPrimaryKey(String haId);

    int insert(Ha record);

    int insertSelective(Ha record);

    Ha selectByPrimaryKey(String haId);

    int updateByPrimaryKeySelective(Ha record);

    int updateByPrimaryKey(Ha record);
    List<Ha> getMyHa(@Param("rder") String rder);
    
    Ha selectHaById(@Param("haId") String haId);
}