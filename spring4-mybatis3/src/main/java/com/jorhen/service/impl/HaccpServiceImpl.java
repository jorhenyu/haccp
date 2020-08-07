package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.HaccpMapper;
import com.jorhen.domain.Haccp;
import com.jorhen.service.HaccpServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("haccpService")
public class HaccpServiceImpl implements HaccpServiceI {

	/**
	 * 使用@Autowired注解標注pcMapper變數， 當需要使用pcMapper時，Spring就會自動注入pcMapper
	 */
	@Autowired
	private HaccpMapper haccpMapper;// 注入dao

	// @Override
	public int insert(Haccp haccp) {
		haccp.setHaccpId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		haccp.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return haccpMapper.insert(haccp);
	}


	// @Override
	public int deleteByPrimaryKey(String haccpId) {
		return haccpMapper.deleteByPrimaryKey(haccpId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Haccp haccp) {
		Date date = new Date();
		haccp.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return haccpMapper.updateByPrimaryKeySelective(haccp);
	}
	
	public Haccp selectByPrimaryKey(String haccpId) {
		return haccpMapper.selectByPrimaryKey(haccpId);		
	}
	
	// @Override
	public List<Haccp> getMyHaccp(String maker) {
		return haccpMapper.getMyHaccp(maker);
	}
	
	public Haccp selectHaccpById(String haccpId) {
		return haccpMapper.selectHaccpById(haccpId);
	}
	
	// @Override
	public List<Haccp> selectHaccpByPlanId(String planId) {
		return haccpMapper.selectHaccpByPlanId(planId);
	}
	
	// @Override
	public List<Haccp> getHaccpByPlanIdDistinct(String rder) {
		return haccpMapper.getHaccpByPlanIdDistinct(rder);
	}

}
