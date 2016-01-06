package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.HardwareDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Hardware;

public class HardwareService {
	
	private HardwareDao hardwareDao;

	public HardwareDao getHardwareDao() {
		return hardwareDao;
	}

	public void setHardwareDao(HardwareDao hardwareDao) {
		this.hardwareDao = hardwareDao;
	}
	
	public void insertHardware(Hardware hardware){
		hardwareDao.insertHardware(hardware);
	}
	
	public void removeHardware(int id){
		hardwareDao.removeHardware(id);
	}
	
	public Hardware getHardwareByid(int id){
		Hardware hardware=hardwareDao.getHardwareByid(id);
		return hardware;
	}
	
	public List<Hardware> getAllHardwares(){
		List<Hardware> hardwares=hardwareDao.getAllHardwares();
		return hardwares;
	}
	
	public List<Hardware> getHardwaresByCondition(int hardware_type,Page page){
		Map<String,Object> parameter = new HashMap<String, Object>();
		Hardware hardware=new Hardware();
		hardware.setHardware_type(hardware_type);
		parameter.put("page", page);
		parameter.put("hardware", hardware);
		return hardwareDao.getHardwaresByPage(parameter);
	}
	
	public Hardware getHardwareByType(int hardware_type){
		
		List<Hardware> hardwares=hardwareDao.getHardwaresByType(hardware_type);
		if (hardwares!=null&&hardwares.size()>0) {
			Hardware hardware=hardwares.get(0);
			return hardware;
		}else {
			return null;
		}
	}

}
