package com.jorhen.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;

import com.jorhen.domain.Customer;
import com.jorhen.domain.Ticket;
 

public class Test {
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
	public static void selectTicketById(int id) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Ticket ticket = (Ticket) session.selectOne(
					"com.jorhen.mapper.TicketMapper.selectTicketById", id);
			if (ticket == null)
				System.out.println("null");
			else {
				System.out.println(ticket);
				System.out.println(ticket.getCustomer());
			}
		} finally {
			session.close();
		}
	}
 
	/*
	 * 一对多关联查询
	 */
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
 
	public static void main(String[] args) {
		System.out.println("==============一对一查询，根据车票来查顾客===============");
		selectTicketById(1);
		System.out.println("==============多对一查询，根据顾客来查车票===============");
		selectCustomerByName("小王");
 
	}
 
}

