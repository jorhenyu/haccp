package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.PcMapper;
import com.jorhen.domain.Pc;
import com.jorhen.domain.Query;
import com.jorhen.service.PcServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("pcService")
public class PcServiceImpl implements PcServiceI {

	/**
	 * 使用@Autowired注解標注pcMapper變數， 當需要使用pcMapper時，Spring就會自動注入pcMapper
	 */
	@Autowired
	private PcMapper pcMapper;// 注入dao

	// @Override
	public int insert(Pc pc) {
		pc.setPcId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		pc.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return pcMapper.insert(pc);
	}


	// @Override
	public int deleteByPrimaryKey(String pcId) {
		return pcMapper.deleteByPrimaryKey(pcId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Pc pc) {
		Date date = new Date();
		pc.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return pcMapper.updateByPrimaryKeySelective(pc);
	}
	
	public Pc selectByPrimaryKey(String pcId) {
		return pcMapper.selectByPrimaryKey(pcId);		
	}
	
	// @Override
	public List<Pc> getMyPc(String rder) {
		return pcMapper.getMyPc(rder);
	}
	
	public Pc selectPcById(String pcId) {
		return pcMapper.selectPcById(pcId);
	}
	
	public Pc getMyPcBypLd(String planId) {
		return pcMapper.getMyPcBypLd(planId);
	}
	
	// @Override
	public List<Pc> getMyPcByQuery(Query query) {
		return pcMapper.getMyPcByQuery(query);
	}

}
