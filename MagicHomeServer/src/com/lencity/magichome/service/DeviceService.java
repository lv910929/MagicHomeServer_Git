package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.DeviceDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Device;
import com.lencity.magichome.model.StateNum;

public class DeviceService {
	
	private DeviceDao deviceDao;
	
	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public void insertDevice(Device device){
		deviceDao.insertDevice(device);
	};
	
	public void updateDevice(Device device){
		deviceDao.updateDevice(device);
	};
	
	public void removeDevice(int id){
		deviceDao.removeDevice(id);
	};
	
	public List<Device> getAllDevices(){
		
		List<Device> devices=deviceDao.getAllDevices();
		return devices;
	};
	
	public Device getDeviceByDomain(String domain_name){
		
		Device device=deviceDao.getDeviceByDomain(domain_name);
		return device;
	};
	
	public Device getDeviceByid(int id){
		
		Device device=deviceDao.getDeviceByid(id);
		return device;
	}
	
	public Device getDeviceByMacAddress(String macAddress){
		
		Device device=deviceDao.getDeviceByMacAddress(macAddress);
		return device;
	}
	
	public Device getDeviceByAccount(String accountName){
		
		Device device=deviceDao.getDeviceByAccount(accountName);
		return device;
	}
	/**
	 * 多条件分页查询
	 */
	public List<Device> getDevicesByPage(String account_name,int device_status,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		Device device=new Device();
		device.setAccount_name(account_name);
		device.setDevice_status(device_status);
		parameter.put("page", page);
		parameter.put("device", device);
		return deviceDao.getDevicesByPage(parameter);
	}
	/**
	 * 更改mac地址
	 */
	public void changeMac(String account_name, String new_macAddress){
		
		Device device=deviceDao.getDeviceByAccount(account_name);
		device.setMac_address(new_macAddress);
		deviceDao.updateDevice(device);
	}
	
	public List<StateNum> getNumByOnline(){
		
		return deviceDao.getNumByOnline();
	}
}
