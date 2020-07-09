package com.jorhen.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.UserMapper;
import com.jorhen.domain.User;
import com.jorhen.exception.UserExistException;
import com.jorhen.service.UserServiceI;
import com.jorhen.util.WebUtils;
import com.jorhen.util.XmlUtils;

/**
 * @author jorhen 使用@Service注解將UserServiceImpl類標注為一個service
 *         service的id是userService
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI {

	/**
	 * 使用@Autowired注解標注userMapper變數， 當需要使用UserMapper時，Spring就會自動注入UserMapper
	 */
	@Autowired
	private UserMapper userMapper;// 注入dao

	// @Override
	public void addUser(User user) {
		user.setuId(WebUtils.makeId());// 設置使用者的Id屬性
		userMapper.insert(user);
	}

	// @Override
	public User getUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	// @Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	// @Override
	public int deleteByPrimaryKey(String userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	// @Override
	public int updateByPrimaryKeySelective(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	// @Override
	public boolean isUserExist(String userName, String userPwd) {

		boolean isUserExist = userMapper.isUserExist(userName, userPwd);

		return isUserExist;
	}
	
	// @Override
	public User findUserByNamePw(String userName, String userPwd) {

		return userMapper.findUserByNamePw(userName, userPwd);
		
	}
}
