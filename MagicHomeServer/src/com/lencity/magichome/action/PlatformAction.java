package com.lencity.magichome.action;

import com.lencity.magichome.model.AdminUser;
import com.lencity.magichome.service.AdminUserService;

@SuppressWarnings("serial")
public class PlatformAction extends BaseAction{

	private AdminUserService adminUserService;

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	private String name;
	
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private AdminUser adminUser;

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	
	public String login() throws Exception{
		adminUser=adminUserService.login(name, password);
		if (null != adminUser)
		{
			request.getSession().setAttribute(SESSION_ADMIN_USER,adminUser);
			return "indexAction";
		} else
		{
			return "adminLogin";
		}
	}
	
	public String regist() throws Exception{
		
		if (!adminUserService.isExist(name)) {
			AdminUser adminUser=new AdminUser();
			adminUser.setName(name);
			adminUser.setPassword(password);
			adminUserService.insertAdminUser(adminUser);
			return "adminLogin";
		}else {
			 String message = "用户名已存在";
			 request.setAttribute("msg", message);
			 return ERROR;
		}
	}
	
	public String logout()
	{
		request.getSession().removeAttribute(SESSION_ADMIN_USER);
		return "adminLogin";
	}

	public String index()
	{
		return "index";
	}

	public String baseInfo()
	{
		return "baseInfo";
	}
	
	public String app(){
		
		return "download";
	}
	
}
