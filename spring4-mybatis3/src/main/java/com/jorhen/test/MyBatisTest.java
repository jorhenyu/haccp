package com.jorhen.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
//import me.gacl.service.UserServiceI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;
import com.jorhen.util.DateUtils;

public class MyBatisTest {

    private UserServiceI userService;
    
    private static Logger logger = Logger.getLogger(MyBatisTest.class.getName());  
    
    /**
     * 這個before方法在所有的測試方法之前執行，並且只執行一次
     * 所有做Junit單元測試時一些初始化工作可以在這個方法裡面進行
     * 比如在before方法裡面初始化ApplicationContext和userService
     */
    @Before
    public void before(){
    	//使用"spring.xml"和"spring-mybatis.xml"這兩個設定檔創建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
      //從Spring容器中根據bean的id取出我們要使用的userService物件
        userService = (UserServiceI) ac.getBean("userService");
    }
    
    @Test
    public void testAddUser(){
        //ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
        //UserServiceI userService = (UserServiceI) ac.getBean("userService");
        User user = new User();
        user.setuId(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setuName("測試A");
        //user.setUserBirthday(new Date());
        user.setuPw("1111111");
        user.setuEmail("aa@gmail.com");
        //user.setUserSalary(10000D);
        userService.addUser(user);
       
        List<User> lstUsers = userService.getAllUser();
       
        System.out.println("===3==");
        
        for(User item : lstUsers){
        	System.out.println(item.getuName());        	
        	
        }
        
        System.out.println("===5==");
        logger.error("测试ss");
        // 记录debug级别的信息  
      logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.11");  
        // 记录error级别的信息  
      //  logger.error("This is error message.");  
        
       // user = userService.loginUser("aa", "1111111");
        
       // System.out.println("===5=="+user.getUserBirthday());
    }
    
    public static void main(String[] args) {
    	 //logger.error("测试");
         //logger.debug("This is debug message.");  
         // 记录info级别的信息  
         //logger.info("This is info message22.");  
         Date date = new Date();
         ;
         logger.info(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD)); 
    	 }
    	 
    
}
