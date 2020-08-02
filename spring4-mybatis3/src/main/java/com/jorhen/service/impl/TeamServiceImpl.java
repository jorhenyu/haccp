package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.TeamMapper;
import com.jorhen.domain.Team;
import com.jorhen.service.TeamServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

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
		team.setTeamId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		team.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		teamMapper.insert(team);
	}

	// @Override
	public Team getTeamById(String teamId) {
		return teamMapper.selectByPrimaryKey(teamId);
	}

	// @Override
	public List<Team> getAllTeam(String rder) {
		return teamMapper.getAllTeam(rder);
	}
	
	// @Override
	public int deleteByPrimaryKey(String teamId) {
		return teamMapper.deleteByPrimaryKey(teamId);
	}
	// @Override
	public int updateByPrimaryKeySelective(Team team) {
		Date date = new Date();
		team.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return teamMapper.updateByPrimaryKeySelective(team);
	}
	
	//@Override
	public Team selectTeamById(String id) {
		return teamMapper.selectTeamById(id);		 
	}


}
