package com.lencity.magichome.model;

public class Account extends BaseModel{
	
	private static final long serialVersionUID = -4458614253615423062L;

	private String accountName;
	
	private String password;
	
	private String mobile;

	public Account() {
		super();
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
