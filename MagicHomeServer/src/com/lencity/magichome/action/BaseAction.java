package com.lencity.magichome.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.AdminUser;
import com.lencity.magichome.model.Log;
import com.lencity.magichome.service.LogService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final String SESSION_ADMIN_USER = "session_admin_user";

	public static final String SESSION_BUSINESS = "session_business";

	public static final String SESSION_USER = "session_user";

	public static final String SESSION_LOGIN_LOG = "session_login_log";
	
	public static final String SESSION_IS_PRINCIPAL = "session_is_principal";

	private String menu;

	private AdminUser curAdminUser;

	private String message;

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public AdminUser getCurAdminUser() {
		curAdminUser = getAdminUserBySession();
		return curAdminUser;
	}

	public void setCurAdminUser(AdminUser curAdminUser) {
		this.curAdminUser = getAdminUserBySession();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	private JSONObject jsonObj;
	
	HttpServletRequest request;

	HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public PrintWriter getOut() throws IOException {
		return response.getWriter();
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	
	public AdminUser getAdminUserBySession() {
		return (AdminUser) request.getSession().getAttribute(
				SESSION_ADMIN_USER);
	}
	
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
	
	public void addLog(String log_content){
		
		Date date = new Date();
		Log log = new Log();
		log.setUser_name(getAdminUserBySession().getName());
		log.setLog_content(log_content);
		log.setLog_time(df.format(date));
		logService.insertLog(log);
	}
	
	protected Page page;
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	protected void init(){
		String currentPage = request.getParameter("currentPage");
		// 创建分页对象
		page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
	}

}
