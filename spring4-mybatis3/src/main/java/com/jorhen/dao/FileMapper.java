package com.jorhen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jorhen.domain.File;
import com.jorhen.domain.Query;

public interface FileMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
    
    List<File> getMyFile(@Param("rder") String rder);
    
    File selectFileById(@Param("fileId") String fileId);
    
    File getMyFileBypLd(@Param("planId") String planId);
    
    List<File> getMyFileByQuery(Query query);
}