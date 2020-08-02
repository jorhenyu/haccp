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
    Team getTeamById(String teamId);
    /**獲取所有類別資訊        
     * @return List<Team>         
     */                           
    List<Team> getAllTeam(String rder);   
    
    int deleteByPrimaryKey(String teamId);
    
    int updateByPrimaryKeySelective(Team team);
    
    Team selectTeamById(String id);


}