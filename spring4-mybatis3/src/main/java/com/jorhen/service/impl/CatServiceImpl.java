package com.jorhen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.CatMapper;
import com.jorhen.domain.Cat;
import com.jorhen.service.CatServiceI;

/**
 * @author jorhen 使用@Service注解將CatServiceImpl類標注為一個service service的id是catService
 */
@Service("catService")
public class CatServiceImpl implements CatServiceI {

	/**
	 * 使用@Autowired注解標注userMapper變數， 當需要使用CatMapper時，Spring就會自動注入CatMapper
	 */
	@Autowired
	private CatMapper catMapper;// 注入dao

	// @Override
	public void addCat(Cat cat) {
		catMapper.insert(cat);
	}

	// @Override
	public Cat getCatById(String cId) {
		return catMapper.selectByPrimaryKey(cId);
	}

	// @Override
	public List<Cat> getAllCat() {
		return catMapper.getAllCat();
	}
	
	// @Override
	public int deleteByPrimaryKey(String cId) {
		return catMapper.deleteByPrimaryKey(cId);
	}
	// @Override
	public int updateByPrimaryKeySelective(Cat cat) {
		return catMapper.updateByPrimaryKeySelective(cat);
	}
	
    //@Override
    public boolean findCatByCatId(String cId) {
   	 boolean isCatExist =  catMapper.findCatByCatId(cId);
        
        return isCatExist;
    }

}
