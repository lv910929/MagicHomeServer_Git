package com.lencity.magichome.model;

import java.util.Date;


public class Hardware extends BaseModel{
	
	private static final long serialVersionUID = 8552659499580270723L;
	
	public static final Integer HARDWARE_APP=1;//程序
	
	public static final Integer TABLE_DATA=2;//表数据

	private int hardware_type;
	
	private int hardware_version;
	
	private String hardware_size;

	private Date upload_time;
	
	private String update_url;
	
	private String hardware_info;

	public int getHardware_type() {
		return hardware_type;
	}

	public void setHardware_type(int hardware_type) {
		this.hardware_type = hardware_type;
	}

	public int getHardware_version() {
		return hardware_version;
	}

	public void setHardware_version(int hardware_version) {
		this.hardware_version = hardware_version;
	}
	
	public String getHardware_size() {
		return hardware_size;
	}

	public void setHardware_size(String hardware_size) {
		this.hardware_size = hardware_size;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getUpdate_url() {
		return update_url;
	}

	public void setUpdate_url(String update_url) {
		this.update_url = update_url;
	}

	public String getHardware_info() {
		return hardware_info;
	}

	public void setHardware_info(String hardware_info) {
		this.hardware_info = hardware_info;
	}
	
	
}
