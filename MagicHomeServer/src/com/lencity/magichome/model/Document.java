package com.lencity.magichome.model;

import java.util.Date;

public class Document extends BaseModel {
	
	// 上传时间
	private Date upload_time;
	// 安装包下载地址
	private String download_url;
	// 升级提示信息
	private String file_info;
	
	public Document() {
		super();
	}
	
	public Document(Date upload_time, String download_url, String file_info) {
		super();
		this.upload_time = upload_time;
		this.download_url = download_url;
		this.file_info = file_info;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getFile_info() {
		return file_info;
	}

	public void setFile_info(String file_info) {
		this.file_info = file_info;
	}
	
}
