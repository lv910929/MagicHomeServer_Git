package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.AdminUser;

public interface AdminUserDao{
	
	public void insertAdminUser(AdminUser adminUser);
	
	public void updateAdminUser(AdminUser adminUser);
	
	public void removeAdminUser(int id);
	
	public AdminUser getAdminUserByid(int id);
	
	public AdminUser getAdminUserByName(String name);
	
	public List<AdminUser> getAdminUsersByPage(Map<String, Object> parameter);
	
	public void updateUserRole(AdminUser adminUser);
	
}
