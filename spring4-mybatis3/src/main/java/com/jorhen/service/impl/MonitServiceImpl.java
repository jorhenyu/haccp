package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.MonitMapper;
import com.jorhen.domain.Monit;
import com.jorhen.domain.Query;
import com.jorhen.service.MonitServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("monitService")
public class MonitServiceImpl implements MonitServiceI {

	/**
	 * 使用@Autowired注解標注pcMapper變數， 當需要使用pcMapper時，Spring就會自動注入pcMapper
	 */
	@Autowired
	private MonitMapper monitMapper;// 注入dao

	// @Override
	public int insert(Monit monit) {
		monit.setMonitId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		monit.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return monitMapper.insert(monit);
	}


	// @Override
	public int deleteByPrimaryKey(String monitId) {
		return monitMapper.deleteByPrimaryKey(monitId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Monit monit) {
		Date date = new Date();
		monit.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return monitMapper.updateByPrimaryKeySelective(monit);
	}
	
	public Monit selectByPrimaryKey(String monitId) {
		return monitMapper.selectByPrimaryKey(monitId);		
	};
	
	// @Override
	public List<Monit> getMyMonit(String maker) {
		return monitMapper.getMyMonit(maker);
	}
	
	public Monit selectMonitById(String ccpId) {
		return monitMapper.selectMonitById(ccpId);
	};
	
	// @Override
	public List<Monit> getMyMonitByQuery(Query query) {
		return monitMapper.getMyMonitByQuery(query);
	}

}
