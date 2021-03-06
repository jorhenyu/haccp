package com.jorhen.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jorhen.dao.TeamMapper;
import com.jorhen.domain.Team;
 

public class Test3 {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/*
	 * 一对一关联查询
	 */
	public static void selectTicketById(String id) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			
			TeamMapper teamMapper = session.getMapper(TeamMapper.class);
			Team team = teamMapper.selectTeamById(id);
			if (team == null)
				System.out.println("null");
			else {
				System.out.println(team);
				System.out.println(team.getPlan());
		        System.out.println(team.getPlan().getMaker());
		        System.out.println(team.getPlan().getpId());
		        System.out.println(team.getPlan().getpName());
			}
		} finally {
			session.close();
		}
	}
 
	/*
	 * 一对多关联查询
	 */
	/*
	public static void selectCustomerByName(String string) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Customer customer = (Customer) session
					.selectOne(
							"com.jorhen.mapper.CustomerMapper.selectCustomerByName",
							string);
			if (customer == null)
				System.out.println("null");
			else {
				System.out.println(customer);
				List<Ticket> tickets = customer.getTickets();
				for (Ticket ticket : tickets) {
					System.out.println(ticket);
				}
			}
		} finally {
			session.close();
		}
	}
 */
	public static void main(String[] args) {
		System.out.println("==============一对一查询，根据车票来查顾客===============");
		selectTicketById("331d39fc499d455a847bc055b5386c1c");
		System.out.println("==============多对一查询，根据顾客来查车票===============");
		//selectCustomerByName("小王");
 
	}
 
}

