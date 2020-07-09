package com.jorhen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.TeamMapper;
import com.jorhen.domain.Team;
import com.jorhen.service.TeamServiceI;

/**
 * @author jorhen 使用@Service注解將TeamServiceImpl類標注為一個service service的id是teamService
 */
@Service("teamService")
public class TeamServiceImpl implements TeamServiceI {

	/**
	 * 使用@Autowired注解標注teamMapper變數， 當需要使用teamMapper時，Spring就會自動注入teamMapper
	 */
	@Autowired
	private TeamMapper teamMapper;// 注入dao

	// @Override
	public void addTeam(Team team) {
		teamMapper.insert(team);
	}

	// @Override
	public Team getTeamById(String planId) {
		return teamMapper.selectByPrimaryKey(planId);
	}

	// @Override
	public List<Team> getAllTeam() {
		return teamMapper.getAllTeam();
	}
	
	// @Override
	public int deleteByPrimaryKey(String planId) {
		return teamMapper.deleteByPrimaryKey(planId);
	}
	// @Override
	public int updateByPrimaryKeySelective(Team team) {
		return teamMapper.updateByPrimaryKeySelective(team);
	}
	
    //@Override
    public boolean findTeamById(String planId) {
   	 boolean isTeamExist =  teamMapper.findTeamById(planId);
        
        return isTeamExist;
    }

}
