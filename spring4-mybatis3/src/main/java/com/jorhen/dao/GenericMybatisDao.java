package com.jorhen.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2020/7/9.
 */


public interface GenericMybatisDao {
    /**
     * 查詢數據通用接口
     * @param param 查詢數據參數
     * @return 查詢結果集
     */
    public List<Map<String,Object>> list(Map<String,Object> param);

    /**
     * 保存數據通用接口
     * @param param 參數
     * @return 修改記錄數量
     */
    public Integer update(Map<String,Object> param);

    /**
     * 保存數據通用接口
     * @param param 參數
     * @return 修改記錄數量
     */
    public Integer insert(Map<String,Object> param);

    /**
     * 刪除數據通用接口
     * @param param 參數
     * @return
     */
    public Integer delete(Map<String,Object> param);
}