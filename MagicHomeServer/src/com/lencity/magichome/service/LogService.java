package com.lencity.magichome.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.LogDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Log;

public class LogService {
	
	private LogDao logDao;

	public LogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	
	public void insertLog(Log log){
		logDao.insertLog(log);
	}
	
	public List<Log> getAllLogs(){
		
		List<Log> logs=new ArrayList<Log>();
		logs=logDao.getAllLogs();
		return logs;
	}
	
	public List<Log> getLogsByPage(String user_name,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		Log log = new Log();
		log.setUser_name(user_name);
		parameter.put("log", log);
		parameter.put("page", page);
		return logDao.getLogsByPage(parameter);
	}

}
