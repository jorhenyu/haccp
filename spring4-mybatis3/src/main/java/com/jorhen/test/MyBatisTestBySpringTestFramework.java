package com.jorhen.test;

import java.util.Date;
import java.util.UUID;
import com.jorhen.domain.User;
import com.jorhen.service.UserServiceI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解並使用該注解的locations屬性指明spring和設定檔之後，
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MyBatisTestBySpringTestFramework {

	//注入userService
    @Autowired
    private UserServiceI userService;
    
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setUserName("測試A");
        //user.setUserBirthday(new Date());
       // user.setUserSalary(10000D);
        userService.addUser(user);
    }
    
    @Test
    public void testGetUserById(){
        String userId = "141cd06e35b04c038f753dcd5ade24ae";
        User user = userService.getUserById(userId);
        System.out.println(user.getUserName());
    }
}
