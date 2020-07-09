package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.User;
import com.jorhen.exception.UserExistException;

public interface UserServiceI {
	 
    /**
     * 添加用戶
     * @param user
     */
    void addUser(User user);
    
    /**
     * 根據使用者id獲取使用者
     * @param userId
     * @return
     */
    User getUserById(String userId);
    /**獲取所有使用者資訊        
     * @return List<User>         
     */                           
    List<User> getAllUser();   
    
    int deleteByPrimaryKey(String userId);
    
    int updateByPrimaryKeySelective(User user);


    /**
     * 提供登錄和使用者名稱找人服務
     * @param userName
     * @param user
     * @return boolean
     */
    boolean isUserExist(String userName, String userPwd);
    
    User findUserByNamePw(String userName, String userPwd);
}