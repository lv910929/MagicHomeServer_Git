package com.lencity.magichome.model;

/**
 * 智能家居服务端管理员类
 * 
 */
public class AdminUser extends BaseModel {

	private static final long serialVersionUID = -4052095167386416410L;
	
	public static final Integer TECHONOGY = 1;
	
	public static final Integer BUSINESS = 2; 
	
	public static final Integer ADMIN = 3;

	private String name;

	private String password;
	
	private Integer user_role;//身份 0：最高管理员 ；1：技术部；2：运营部

	public AdminUser() {
		super();
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
	
	

}
