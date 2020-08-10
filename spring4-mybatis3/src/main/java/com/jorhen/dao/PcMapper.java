package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Pc;
import com.jorhen.domain.Query;

public interface PcMapper {
    int deleteByPrimaryKey(String pcId);

    int insert(Pc record);

    int insertSelective(Pc record);

    Pc selectByPrimaryKey(String pcId);

    int updateByPrimaryKeySelective(Pc record);

    int updateByPrimaryKey(Pc record);
    
    List<Pc> getMyPc(@Param("rder") String rder);
    
    Pc selectPcById(@Param("pcId") String pcId);
    
    Pc getMyPcBypLd(@Param("planId") String planId);
    
    List<Pc> getMyPcByQuery(Query query);
}