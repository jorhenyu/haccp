package com.jorhen.service;

import java.util.List;

import com.jorhen.domain.File;
import com.jorhen.domain.Query;


public interface FileServiceI {
	
	  int deleteByPrimaryKey(String fileId);

	    int insert(File record);

	    //int insertSelective(File record);

	    File selectByPrimaryKey(String fileId);

	    int updateByPrimaryKeySelective(File record);

	   // int updateByPrimaryKey(File record);
	    
	    List<File> getMyFile(String rder);
	    
	    File selectFileById(String fileId);
	    
	    File getMyFileBypLd(String planId);
	    
	    List<File> getMyFileByQuery(Query query);


}