package com.jorhen.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jorhen.domain.Team;
import com.jorhen.service.OptionService;
import com.jorhen.service.TeamServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解並使用該注解的locations屬性指明spring和設定檔之後，
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class MyBatisTestBySpringTestFramework {

	//注入userService
//    @Autowired
 //   private UserServiceI userService;
    
    //@Autowired
   // OptionService optionService;
    
    
    @Autowired
   private TeamServiceI teamService;
    /*
    @Test
    public void testAddUser(){
        User user = new User();
       user.setuId(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setuName("測試A");
        //user.setUserBirthday(new Date());
       // user.setUserSalary(10000D);
        userService.addUser(user);
    }
    
    @Test
    public void testGetUserById(){
        String userId = "141cd06e35b04c038f753dcd5ade24ae";
        User user = userService.getUserById(userId);
        System.out.println(user.getuName());
    }
    */
    
    @Test
    public void testCat(){
    	/*
        String condition = "";
        String order = "";
        Map<String,Object> param = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(condition)){
            param.put("condition",condition);
        }
        if(StringUtils.isNotBlank(condition)){
            param.put("order",order);
        }
        
        List<Map<String, Object>> aa =  this.optionService.planStatusOption();
        for(Map<String, Object>  m : aa){ 
        	 
        	for (String k : m.keySet()) 

        	{ 

        	System.out.println(k + " : " + m.get(k)); 

        	} 
        } 
    	*/
        Team team = teamService.selectTeamById("331d39fc499d455a847bc055b5386c1c");
        System.out.println(team.getPlanId());
        System.out.println(team.getPlan());
        System.out.println(team.getPlan().getcId());
       System.out.println(team.getPlan().getpId());
       System.out.println(team.getPlan().getpName());
        
    	
    }
    

    
    
}
