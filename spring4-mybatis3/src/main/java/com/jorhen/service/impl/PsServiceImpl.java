package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.PsMapper;
import com.jorhen.domain.Plan;
import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;
import com.jorhen.service.PsServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("psService")
public class PsServiceImpl implements PsServiceI {

	/**
	 * 使用@Autowired注解標注psMapper變數， 當需要使用psMapper時，Spring就會自動注入psMapper
	 */
	@Autowired
	private PsMapper psMapper;// 注入dao

	// @Override
	public int insert(Ps ps) {
		ps.setPsId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		ps.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return psMapper.insert(ps);
	}


	// @Override
	public int deleteByPrimaryKey(String psId) {
		return psMapper.deleteByPrimaryKey(psId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Ps ps) {
		Date date = new Date();
		ps.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return psMapper.updateByPrimaryKeySelective(ps);
	}
	
	public Ps selectByPrimaryKey(String psId) {
		return psMapper.selectByPrimaryKey(psId);		
	}
	
	// @Override
	public List<Ps> getMyPs(String rder) {
		return psMapper.getMyPs(rder);
	}
	
	public Ps selectPsById(String psId) {
		return psMapper.selectPsById(psId);
	}
	
	public Ps getMyPsBypLd(String planId) {
		return psMapper.getMyPsBypLd(planId);
	}
	
	// @Override
	public List<Ps> getMyPsByQuery(Query query) {
		return psMapper.getMyPsByQuery(query);
	}

}
