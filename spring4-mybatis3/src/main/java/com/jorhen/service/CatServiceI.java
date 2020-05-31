package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.Cat;


public interface CatServiceI {
	 
    /**
     * 添加類別
     * @param cat
     */
    void addCat(Cat cat);
    
    /**
     * 根據使用者id獲取類別
     * @param userId
     * @return
     */
    Cat getCatById(String cId);
    /**獲取所有類別資訊        
     * @return List<Cat>         
     */                           
    List<Cat> getAllCat();   
    
    int deleteByPrimaryKey(String cId);
    
    int updateByPrimaryKeySelective(Cat cat);
    
    /**
     * 
     * @param cId
     * 
     * @return boolean
     */
    boolean findCatByCatId(String cId);


}