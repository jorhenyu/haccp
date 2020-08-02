package com.jorhen.service;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
        * @since 2020/7/9.
        */
public interface OptionService {
	//類別下拉選單
    public List<Map<String,Object>>  catsTypeOption(Map<String,Object> param);
    //激化書狀態下拉選單
    public List<Map<String,Object>>  planStatusOption();
}