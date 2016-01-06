package com.lencity.magichome.model;

import java.util.Date;

public class Software extends BaseModel{

	private static final long serialVersionUID = 1591755018836935467L;
	
	public static final Integer APP_TYPE_CENTER = 1;

	public static final Integer APP_TYPE_ANDROID_PHONE = 2;

	public static final Integer APP_TYPE_ANDROID_PAD = 3;
	//应用类型
	private int software_type;
	// 应用版本
	private int software_version;
	// 应用安装包大小
	private String software_size;
	// 上传时间
	private Date release_time;
	// 安装包下载地址
	private String download_url;
	// 升级提示信息
	private String software_info;
	
	public Software() {
		super();
		
	}
	
	public int getSoftware_type() {
		return software_type;
	}

	public void setSoftware_type(int software_type) {
		this.software_type = software_type;
	}

	public int getSoftware_version() {
		return software_version;
	}
	public void setSoftware_version(int software_version) {
		this.software_version = software_version;
	}
	
	public String getSoftware_size() {
		return software_size;
	}

	public void setSoftware_size(String software_size) {
		this.software_size = software_size;
	}

	public Date getRelease_time() {
		return (null != this.release_time) ? this.release_time : new Date();
	}
	public void setRelease_time(Date release_time) {
		this.release_time = release_time;
	}
	
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getSoftware_info() {
		return software_info;
	}

	public void setSoftware_info(String software_info) {
		this.software_info = software_info;
	}
	

	
}
