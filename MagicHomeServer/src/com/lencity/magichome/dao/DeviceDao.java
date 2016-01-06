package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Device;
import com.lencity.magichome.model.StateNum;

public interface DeviceDao {
	
	public void insertDevice(Device device);
	
	public void updateDevice(Device device);
	
	public void removeDevice(int id);
	
	public List<Device> getAllDevices();
	
	public Device getDeviceByid(int id);
	
	public Device getDeviceByMacAddress(String macAddress);
	
	public Device getDeviceByDomain(String domain_name);
	
	public List<Device> getDevicesByPage(Map<String,Object> parameter);

	public Device getDeviceByAccount(String accountName);
	
	public List<StateNum> getNumByOnline();

}
