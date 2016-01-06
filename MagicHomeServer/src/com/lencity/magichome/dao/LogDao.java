package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Log;

public interface LogDao {
	
	public void insertLog(Log log);
	
	public List<Log> getAllLogs();

	public List<Log> getLogsByPage(Map<String,Object> parameter);
}
