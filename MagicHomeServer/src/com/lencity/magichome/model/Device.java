package com.lencity.magichome.model;

import java.util.Date;

public class Device extends BaseModel{
	
	private static final long serialVersionUID = -2616177539722020509L;

	public static final Integer ON_LINE=1;//联网中
	
	public static final Integer OFF_LINE=2;//未联网
	
	private String account_name;
	
	private String mac_address;
		
	private String domain_name;
	
	private String sn_code;
	
	private int software_version;
	
	private int hardware_version;
	
	private Date register_time;
	
	private Date update_time;
	
	private Integer device_status;

	public Device() {
		super();
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getSn_code() {
		return sn_code;
	}

	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}

	public int getSoftware_version() {
		return software_version;
	}

	public void setSoftware_version(int software_version) {
		this.software_version = software_version;
	}

	public int getHardware_version() {
		return hardware_version;
	}

	public void setHardware_version(int hardware_version) {
		this.hardware_version = hardware_version;
	}

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public int getDevice_status() {
		return device_status;
	}

	public void setDevice_status(int device_status) {
		this.device_status = device_status;
	}

}
