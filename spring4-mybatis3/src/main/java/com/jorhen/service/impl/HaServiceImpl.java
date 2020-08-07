package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.HaMapper;
import com.jorhen.domain.Ha;
import com.jorhen.service.HaServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("haService")
public class HaServiceImpl implements HaServiceI {

	/**
	 * 使用@Autowired注解標注pcMapper變數， 當需要使用pcMapper時，Spring就會自動注入pcMapper
	 */
	@Autowired
	private HaMapper haMapper;// 注入dao

	// @Override
	public int insert(Ha ha) {
		ha.setHaId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		ha.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return haMapper.insert(ha);
	}


	// @Override
	public int deleteByPrimaryKey(String haId) {
		return haMapper.deleteByPrimaryKey(haId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Ha ha) {
		Date date = new Date();
		ha.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return haMapper.updateByPrimaryKeySelective(ha);
	}
	
	public Ha selectByPrimaryKey(String haId) {
		return haMapper.selectByPrimaryKey(haId);		
	}
	
	// @Override
	public List<Ha> getMyHa(String maker) {
		return haMapper.getMyHa(maker);
	}
	
	public Ha selectHaById(String haId) {
		return haMapper.selectHaById(haId);
	}
	
	// @Override
	public List<Ha> selectHaByPlanId(String planId) {
		return haMapper.selectHaByPlanId(planId);
	}
	
	// @Override
	public List<Ha> getHaByPlanIdDistinct(String rder) {
		return haMapper.getHaByPlanIdDistinct(rder);
	}

}
