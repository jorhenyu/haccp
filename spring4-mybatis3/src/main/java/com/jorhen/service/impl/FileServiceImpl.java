package com.jorhen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorhen.dao.FileMapper;
import com.jorhen.domain.File;
import com.jorhen.domain.Query;
import com.jorhen.service.FileServiceI;
import com.jorhen.util.DateUtils;
import com.jorhen.util.WebUtils;

/**
 * @author jorhen 使用@Service注解將PlanServiceImpl類標注為一個service
 *         service的id是planService
 */
@Service("fileService")
public class FileServiceImpl implements FileServiceI {

	/**
	 * 使用@Autowired注解標注pcMapper變數， 當需要使用pcMapper時，Spring就會自動注入pcMapper
	 */
	@Autowired
	private FileMapper fileMapper;// 注入dao

	// @Override
	public int insert(File file) {
		file.setFileId(WebUtils.makeId());//設置的Id屬性		
		Date date = new Date();
		file.setrDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return fileMapper.insert(file);
	}


	// @Override
	public int deleteByPrimaryKey(String fileId) {
		return fileMapper.deleteByPrimaryKey(fileId);
	}

	// @Override
	public int updateByPrimaryKeySelective(File file) {
		Date date = new Date();
		file.setmDate(DateUtils.formatDateToString(date, DateUtils.DATE_FORMAT_YMD));
		return fileMapper.updateByPrimaryKeySelective(file);
	}
	
	public File selectByPrimaryKey(String fileId) {
		return fileMapper.selectByPrimaryKey(fileId);		
	}
	
	// @Override
	public List<File> getMyFile(String rder) {
		return fileMapper.getMyFile(rder);
	}
	
	public File selectFileById(String fileId) {
		return fileMapper.selectFileById(fileId);
	}
	
	public File getMyFileBypLd(String planId) {
		return fileMapper.getMyFileBypLd(planId);
	}
	
	// @Override
	public List<File> getMyFileByQuery(Query query) {
		return fileMapper.getMyFileByQuery(query);
	}

}
