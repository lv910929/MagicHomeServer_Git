package com.lencity.magichome.action;

import java.util.List;

import com.lencity.magichome.model.Log;
import com.lencity.magichome.service.LogService;

public class DBLogManagerAction extends BaseAction{
	
	private static final long serialVersionUID = -5353960438567446529L;

	private List<Log> logs;

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
	
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	private int id;
	
	private String user_name;
	
	private String log_content;
	
	private String log_time;
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLog_content() {
		return log_content;
	}

	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}

	public String getLog_time() {
		return log_time;
	}

	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}
	
	/**
	 * 分页获取日志信息
	 */
	public String list(){
		init();
		logs = logService.getLogsByPage(user_name, page);
		return "list";
	}

}
