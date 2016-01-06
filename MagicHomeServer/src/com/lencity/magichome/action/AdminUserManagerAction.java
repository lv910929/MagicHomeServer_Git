package com.lencity.magichome.action;

import java.util.List;

import com.lencity.magichome.model.AdminUser;
import com.lencity.magichome.service.AdminUserService;

@SuppressWarnings("serial")
public class AdminUserManagerAction extends BaseAction{
	
	private AdminUserService adminUserService;
	
	private AdminUser adminUser;
	
	private List<AdminUser> adminUsers;
	
	private int id;
	
	private String name;
	
	private String password;
	
	private Integer user_role;
		
	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Integer getUser_role() {
		return user_role;
	}

	public void setUser_role(Integer user_role) {
		this.user_role = user_role;
	}

	public List<AdminUser> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(List<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}

	public String list(){
		init();
		adminUsers=adminUserService.getAdminUsersByPage(page);
		return "list";
	}
	
	public String remove(){
		
		AdminUser adminUser=adminUserService.getAdminUserByid(id);
		addLog("删除了管理员:"+adminUser.getName());
		adminUserService.removeAdminUser(id);
		init();
		adminUsers=adminUserService.getAdminUsersByPage(page);
		return "list";
	}
	
	public String changeRole(){
		
		addLog("修改了管理员："+name+"的权限");
		adminUserService.changeRole(name, user_role);
		init();
		adminUsers=adminUserService.getAdminUsersByPage(page);
		return "list";
	}
	
}
