package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.CcpMapper;
import com.jorhen.domain.Ccp;
import com.jorhen.domain.Query;
import com.jorhen.service.CcpServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("ccpService")
public class CcpServiceImpl implements CcpServiceI {

	/**
	 * 使用@Autowired注解標注pcMapper變數， 當需要使用pcMapper時，Spring就會自動注入pcMapper
	 */
	@Autowired
	private CcpMapper ccpMapper;// 注入dao

	// @Override
	public int insert(Ccp ccp) {
		ccp.setCcpId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		ccp.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return ccpMapper.insert(ccp);
	}


	// @Override
	public int deleteByPrimaryKey(String ccpId) {
		return ccpMapper.deleteByPrimaryKey(ccpId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Ccp ccp) {
		Date date = new Date();
		ccp.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return ccpMapper.updateByPrimaryKeySelective(ccp);
	}
	
	public Ccp selectByPrimaryKey(String ccpId) {
		return ccpMapper.selectByPrimaryKey(ccpId);		
	}
	
	// @Override
	public List<Ccp> getMyCcp(String maker) {
		return ccpMapper.getMyCcp(maker);
	}
	
	public Ccp selectCcpById(String ccpId) {
		return ccpMapper.selectCcpById(ccpId);
	}
	
	// @Override
	public List<Ccp> selectSHaByPname(String pName, String rder) {
		return ccpMapper.selectSHaByPname(pName, rder);
	}
	
	// @Override
	public List<Ccp> selectCcpByPlanId(String planId) {
		return ccpMapper.selectCcpByPlanId(planId);
	}
	
	// @Override
	public List<Ccp> getCcpByPlanIdDistinct(String rder) {
		return ccpMapper.getCcpByPlanIdDistinct(rder);
	}
	
	// @Override
	public List<Ccp> getMyCcpByQuery(Query query) {
		return ccpMapper.getMyCcpByQuery(query);
	}

}
