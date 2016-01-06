package com.lencity.magichome.model;

import java.util.Date;

public class Feedback extends BaseModel{

	private static final long serialVersionUID = -6224383957793687905L;

	public static final Integer FEEDBACK_UNREAD=1;
	
	public static final Integer FEEDBACK_READ=2;
	
	private String account_name;
	
	private String content;
	
	private Date create_time;
	
	private Integer status;

	public Feedback() {
		super();
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return (null != this.create_time) ? this.create_time : new Date();
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
