package com.lencity.magichome.action;

import java.util.Date;
import java.util.List;

import com.lencity.magichome.model.Device;
import com.lencity.magichome.service.DeviceService;

@SuppressWarnings("serial")
public class DeviceManagerAction extends BaseAction {

	private DeviceService deviceService;

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	private List<Device> devices;

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	private int id;

	private String account_name;
	
	private String mac_address;
	
	private String new_macAddress;

	private String domain_name;
	
	private String sn_code;

	private int software_version;

	private int hardware_version;

	private Date register_time;

	private Date update_time;

	private int device_status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNew_macAddress() {
		return new_macAddress;
	}

	public void setNew_macAddress(String new_macAddress) {
		this.new_macAddress = new_macAddress;
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
	
	/**
	 * 分页获取设备信息
	 */
	public String list() {

		init();
		devices=deviceService.getDevicesByPage(account_name, device_status, page);
		return "list";
	}
	
	/**
	 * 更改mac地址
	 */
	public String changeMac(){
		
		addLog("修改了中控设备的mac地址："+new_macAddress);
		deviceService.changeMac(account_name, new_macAddress);
		init();
		devices=deviceService.getDevicesByPage(account_name, device_status, page);
		return "list";
	}
}
