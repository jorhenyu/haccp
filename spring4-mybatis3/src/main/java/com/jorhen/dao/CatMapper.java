package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.Cat;


public interface CatMapper {
    int deleteByPrimaryKey(String cId);

    int insert(Cat record);

    int insertSelective(Cat record);

    Cat selectByPrimaryKey(String cId);

    int updateByPrimaryKeySelective(Cat record);

    int updateByPrimaryKey(Cat record);
    
    /**獲取所有類別資訊       
     * @return List<Cat>       
     */                          
    List<Cat> getAllCat();
    
    boolean findCatByCatId(@Param("cId") String cId);
}