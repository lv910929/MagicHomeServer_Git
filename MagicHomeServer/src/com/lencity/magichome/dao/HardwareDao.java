package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Hardware;

public interface HardwareDao {
	
	public void insertHardware(Hardware hardware);
	
	public void removeHardware(int id);
	
	public List<Hardware> getAllHardwares();
	
	public List<Hardware> getHardwaresByPage(Map<String, Object> parameter);
	
	public Hardware getHardwareByid(int id);
	
	public List<Hardware> getHardwaresByType(int hardware_type);

}
