package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Plan;


public interface PlanServiceI {
	
    int deleteByPrimaryKey(String pId);
    
    int updateByPrimaryKeySelective(Plan plan);
	 
    /**
     * 添加 plan
     * @param plan
     */
    void add(Plan plan);
    
    /**
     * 根據id獲取plan
     * @param pId
     * @return
     */
    Plan getById(String pId);
    /**獲取maker所有資訊        
     * @return List<Plan>         
     */                           
    List<Plan> getMyPlan(String maker);     

    
    /**
     * 
     * @param pId
     * 
     * @return boolean
     */
    boolean findById(String pId);


}