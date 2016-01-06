package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.AdminUserDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.AdminUser;
import com.lencity.magichome.util.Md5;

public class AdminUserService {
	
	private AdminUserDao adminUserDao;

	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	public void insertAdminUser(AdminUser adminUser) throws Exception{
		adminUser.setPassword(Md5.md5(adminUser.getName()+adminUser.getPassword()));
		adminUserDao.insertAdminUser(adminUser);
	}

	public void removeAdminUser(int id){
		adminUserDao.removeAdminUser(id);
	}
	
	public AdminUser getAdminUserByid(int id){
		AdminUser result=adminUserDao.getAdminUserByid(id);
		return result;
	}
	
	/**
	 * 修改密码
	 * @throws Exception 
	 */
	public void changePassword(String name,String newPassword) throws Exception{
		
		AdminUser adminUser=adminUserDao.getAdminUserByName(name);
		if (null!=adminUser) {
			adminUser.setPassword(Md5.md5(adminUser.getName()+newPassword));
			adminUserDao.updateAdminUser(adminUser);
		}
	}
	/**
	 * 修改用户权限
	 */
	public void changeRole(String name,int user_role){
		AdminUser adminUser=adminUserDao.getAdminUserByName(name);
		adminUser.setUser_role(user_role);
		adminUserDao.updateUserRole(adminUser);
	}
	
	public List<AdminUser> getAdminUsersByPage(Page page){
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("page", page);
		List<AdminUser> adminUsers=adminUserDao.getAdminUsersByPage(parameter);
		return adminUsers;
	}
	
	public boolean isExist(String name){
		
		AdminUser adminUser=adminUserDao.getAdminUserByName(name);
		if (adminUser!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public AdminUser login(String name,String password) throws Exception{
		
		AdminUser adminUser=adminUserDao.getAdminUserByName(name);
		if (adminUser!=null) {
			password=Md5.md5(name+password);
			if (adminUser.getPassword().equals(password)) {
				return adminUser;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}
