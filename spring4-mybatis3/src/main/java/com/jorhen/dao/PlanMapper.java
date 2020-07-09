package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Plan;

public interface PlanMapper {
    int deleteByPrimaryKey(String pId);

    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(String pId);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);
    
    /**獲取所有maker資訊       
     * @return List<Plan>       
     */                          
    List<Plan> getMyPlan(@Param("rder") String rder);
    
    boolean findById(@Param("pId") String pId);
    

}