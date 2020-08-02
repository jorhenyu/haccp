package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Team;
import org.apache.ibatis.annotations.Mapper;

public interface TeamMapper {
    int deleteByPrimaryKey(String teamId);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(String teamId);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
    
    /**獲取所有類別資訊       
     * @return List<Team>       
     */                          
    List<Team> getAllTeam(@Param("rder") String rder);
    
    /*
     * 根據 id 查詢 team
     * @param id
     * @return
     * 
     */
    Team selectTeamById(String id);

    

}