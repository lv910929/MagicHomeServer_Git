package com.lencity.magichome.service;

import java.util.List;

import com.lencity.magichome.dao.DeviceNumDao;
import com.lencity.magichome.model.DeviceNum;

public class DeviceNumService {

	private DeviceNumDao deviceNumDao;

	public DeviceNumDao getDeviceNumDao() {
		return deviceNumDao;
	}

	public void setDeviceNumDao(DeviceNumDao deviceNumDao) {
		this.deviceNumDao = deviceNumDao;
	}
	
	public void updateDeviceNum(DeviceNum deviceNum){
		deviceNumDao.updateDeviceNum(deviceNum);
	}
	
	public List<DeviceNum> getDeviceNums(){
		return deviceNumDao.getDeviceNums();
	}
}
