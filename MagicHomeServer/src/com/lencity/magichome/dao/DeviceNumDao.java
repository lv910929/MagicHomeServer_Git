package com.lencity.magichome.dao;

import java.util.List;

import com.lencity.magichome.model.DeviceNum;

public interface DeviceNumDao {
	
	public void updateDeviceNum(DeviceNum deviceNum);

	public List<DeviceNum> getDeviceNums();
}
