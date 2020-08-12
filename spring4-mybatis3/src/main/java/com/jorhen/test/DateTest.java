package com.jorhen.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jorhen.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class DateTest {
	private static Logger log = Logger.getLogger(PlanCopyTest.class);
	@Test	    
	public  void doDate() {
		
		String aa = DateUtils.formatDateToyyyymmdd("2020-08-10");
		log.info("=s=aa==" +aa);
	}
	

}
