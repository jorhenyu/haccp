package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Team;


public interface TeamServiceI {
	 
    /**
     * 添加
     * @param team
     */
    void addTeam(Team team);
    
    /**
     * 根據planId獲取team info
     * @param planId
     * @return
     */
    Team getTeamById(String planId);
    /**獲取所有類別資訊        
     * @return List<Team>         
     */                           
    List<Team> getAllTeam();   
    
    int deleteByPrimaryKey(String planId);
    
    int updateByPrimaryKeySelective(Team team);
    
    /**
     * 
     * @param planId
     * 
     * @return boolean
     */
    boolean findTeamById(String planId);


}