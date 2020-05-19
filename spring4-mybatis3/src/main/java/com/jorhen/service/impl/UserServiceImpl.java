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
 * @author jorhen 使用@Service注解將UserServiceImpl類標注為一個service service的id是userService
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
		user.setUserId(WebUtils.makeId());//設置使用者的Id屬性
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
/*
	// @Override
	public User find(String userName, String userPwd) {
		try {
			/*Document document = XmlUtils.getDocument();
			// 使用XPath運算式來操作XML節點
			//Element e = (Element) document
			//		.selectSingleNode("//user[@userName='" + userName + "' and @userPwd='" + userPwd + "']");
			//if (e == null) {
			//	return null;
			//}
			User user = new User();
			user.setUserId(e.attributeValue("id"));
			user.setUserEmail(e.attributeValue("email"));
			user.setUserPwd(e.attributeValue("userPwd"));
			user.setUserName(e.attributeValue("userName"));
			String birth = e.attributeValue("birthday");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setUserBirthday(sdf.parse(birth));

			User user = new User();

			return user;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// @SuppressWarnings("deprecation")
	// @Override
	public User find(String userName) {
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@userName='" + userName + "']");
			if (e == null) {
				return null;
			}
			User user = new User();
			user.setUserId(e.attributeValue("id"));
			user.setUserEmail(e.attributeValue("email"));
			user.setUserPwd(e.attributeValue("userPwd"));
			user.setUserName(e.attributeValue("userName"));
			String birth = e.attributeValue("birthday");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setUserBirthday(sdf.parse(birth));

			return user;

		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}
	
	   //@Override
	     public void registerUser(User user) throws UserExistException {
	         if (find(user.getUserName())!=null) {
	             //checked exception 
	             //unchecked exception
	             //這裡拋編譯時異常的原因：是我想上一層程式處理這個異常，以給用戶一個友好提示
	             throw new UserExistException("註冊的用戶名已存在！！！");
	         }
	         user.setUserId(WebUtils.makeId());//設置使用者的Id屬性
	         addUser(user);
	     }
	     */
	 
	     //@Override
	     public boolean findUserByUsername(String userName, String userPwd) {
	       //  System.out.println("username=="+userName);
	        // System.out.println("password=="+userPwd);
	         //User user = new User();
	         //user = userMapper.selectByPrimaryKey(userId);
	         
	         //System.out.println("username1=="+user.getUserName());
	        // System.out.println("password=="+user.getUserPwd());
	         //User user = new User();
	    	 boolean isUserExist =  userMapper.findUserByUsername(userName, userPwd);
	        // user = userMapper.selectByPrimaryKey("f9b9e31f081249978ee0aabc6ebf4eb4");
	        // System.out.println("===6665=="+user.getUserBirthday());
	         
	         return isUserExist;
	     }
}
