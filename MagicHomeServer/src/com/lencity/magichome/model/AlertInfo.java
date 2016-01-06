package com.lencity.magichome.model;

import java.util.Date;


public class AlertInfo extends BaseModel{

	private static final long serialVersionUID = 1756163944708882409L;
	
	private String account;
	
	private Date push_time;
	
	private String alert_info;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getPush_time() {
		return push_time;
	}

	public void setPush_time(java.util.Date date) {
		this.push_time = date;
	}

	public String getAlert_info() {
		return alert_info;
	}

	public void setAlert_info(String alert_info) {
		this.alert_info = alert_info;
	}
	

}
