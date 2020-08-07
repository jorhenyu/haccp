package com.jorhen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.GenericMybatisDao;
import com.jorhen.service.OptionService;

/**
 * 下拉選項數據列表
 * 
 * @author Jason
 * @since 2020/7/11.
 * @see OptionService
 * @see Service
 *
 */
@Service("optionService")
public class OptionServiceImpl implements OptionService {

	@Autowired
	GenericMybatisDao dao;

	// @Override
	public List<Map<String, Object>> catsTypeOption(Map<String, Object> param) {
		return findData(OptionQueryEnum.CATS_TYPE_OPTION.getQuery(), param);
	}

	// @Override
	public List<Map<String, Object>> planStatusOption() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();		
		
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("optionKey", "fopen");
		map.put("optionValue", "公開");
		mapList.add(map);
		map = new HashMap<String, Object>();
		map.put("optionKey", "fprivate");
		map.put("optionValue", "私人");
		mapList.add(map);
		map = new HashMap<String, Object>();
		map.put("optionKey", "fcowork");
		map.put("optionValue", "協作");
		mapList.add(map);		


		return mapList;
	}

	public List<Map<String, Object>> findData(String querySql, Map<String, Object> param) {
		StringBuilder sql = new StringBuilder(querySql);
		Map<String, Object> map = new HashMap<String, Object>();
		if (param != null) {
			if (param.get("condition") != null) {
				String[] conditions = param.get("condition").toString().split(",");
				for (String condition : conditions) {
					sql.append(" and ");
					sql.append(condition);
				}
			}
			if (param.get("order") != null) {
				sql.append(" order by ");
				sql.append(param.get("order"));
			}
		}
		map.put("sqltext", sql);
		return this.dao.list(map);
	}

	enum OptionQueryEnum {
		CATS_TYPE_OPTION(" select c_id as optionKey,c_name as optionValue from t_cat "), BRAND_OTION(
				" select brand_id as optionKey,brand_name as optionValue from ecs_brand where 1=1 ");
		private String query;

		private OptionQueryEnum(String query) {
			this.query = query;
		}

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}
	}

}