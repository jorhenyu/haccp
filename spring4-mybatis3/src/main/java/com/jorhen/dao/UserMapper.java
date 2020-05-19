package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    
    
    /**獲取所有使用者資訊       
     * @return List<User>       
     */                          
    List<User> getAllUser();    
    
   
    boolean findUserByUsername(@Param("userName") String userName, @Param("userPwd") String userPwd);

}