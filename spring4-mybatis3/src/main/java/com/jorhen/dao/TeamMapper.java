package com.jorhen.dao;

import java.util.List;

import com.jorhen.domain.Team;

public interface TeamMapper {
    int deleteByPrimaryKey(String planId);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(String planId);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
    
    /**獲取所有類別資訊       
     * @return List<Team>       
     */                          
    List<Team> getAllTeam();
    
    /**
     * 
     * @param planId
     * 
     * @return boolean
     */
    boolean findTeamById(String planId);
}