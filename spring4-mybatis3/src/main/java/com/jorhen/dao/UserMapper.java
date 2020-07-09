package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**獲取所有使用者資訊       
     * @return List<User>       
     */                          
    List<User> getAllUser();    
    
   
    boolean isUserExist(@Param("uName") String uName, @Param("uPw") String uPw);
    
    User findUserByNamePw(@Param("uName") String uName, @Param("uPw") String uPw);

}