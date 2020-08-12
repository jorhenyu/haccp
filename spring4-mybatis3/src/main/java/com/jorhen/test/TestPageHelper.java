package com.jorhen.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jorhen.domain.Ha;
import com.jorhen.service.HaServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestPageHelper {
	// 設置log
	private static Logger log = Logger.getLogger(PlanCopyTest.class); 
	@Autowired
	private HaServiceI haService;
	
	@Test
	   public void testPageHelper() {
		/*
        // 创建一个spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*");
        // 从spring容器中获取mapper代理对象
        TbItemMapper mapper = context.getBean(TbItemMapper.class);
        // 执行查询并分页,TbItemExample是逆向工程自动生成的，用来进行条件查询，这里不设置则表示无条件
        TbItemExample example = new TbItemExample();
        //分页处理，显示第一页的10条数据
        PageHelper.startPage(1, 10);
        List<TbItem> list = mapper.selectByExample(example);//查询
        // 取商品列表
        for(TbItem item : list) {
            System.out.println(item.getTitle());
        }
        // 取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        long total = pageInfo.getTotal(); //获取总记录数
        System.out.println("共有商品信息：" + total);
		*/
		PageHelper.startPage(2, 5);
		List<Ha> list = haService.getMyHa("admin");
        // 取商品列表
        for(Ha item : list) {
            System.out.println(item.getProcStep());
        }
        
        // 取分页信息
        PageInfo<Ha> pageInfo = new PageInfo<Ha>(list);
        long total = pageInfo.getTotal(); //获取总记录数
        System.out.println("共有商品信息：" + total);
        System.out.println("當前頁："+pageInfo.getPageNum());
        System.out.println("頁面大小："+pageInfo.getPageSize());
        System.out.println("總數："+pageInfo.getTotal());	
        System.out.println("總頁數："+pageInfo.getPages());
		
	    }

}
