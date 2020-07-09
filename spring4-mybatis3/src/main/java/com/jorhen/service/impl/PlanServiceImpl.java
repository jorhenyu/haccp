package com.jorhen.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.PlanMapper;
import com.jorhen.domain.Plan;
import com.jorhen.service.PlanServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;
import java.util.Date;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("planService")
public class PlanServiceImpl implements PlanServiceI {

	/**
	 * 使用@Autowired注解標注planMapper變數， 當需要使用planMapper時，Spring就會自動注入planMapper
	 */
	@Autowired
	private PlanMapper planMapper;// 注入dao

	// @Override
	public void add(Plan plan) {
		plan.setpId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		plan.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		planMapper.insert(plan);
	}

	// @Override
	public Plan getById(String pId) {
		return planMapper.selectByPrimaryKey(pId);
	}

	// @Override
	public List<Plan> getMyPlan(String maker) {
		return planMapper.getMyPlan(maker);
	}

	// @Override
	public int deleteByPrimaryKey(String pId) {
		return planMapper.deleteByPrimaryKey(pId);
	}

	// @Override
	public int updateByPrimaryKeySelective(Plan plan) {
		Date date = new Date();
		plan.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return planMapper.updateByPrimaryKeySelective(plan);
	}

	// @Override
	public boolean findById(String pId) {

		return planMapper.findById(pId);
	}

}
