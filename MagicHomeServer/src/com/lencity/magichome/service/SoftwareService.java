package com.lencity.magichome.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.SoftwareDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Software;

public class SoftwareService{
	
	private SoftwareDao softwareDao;

	public SoftwareDao getSoftwareDao() {
		return softwareDao;
	}

	public void setSoftwareDao(SoftwareDao softwareDao) {
		this.softwareDao = softwareDao;
	}
	/**
	 * 上传新的版本软件
	 */
	public void insertSoftware(Software software) {
		
		softwareDao.insertSoftware(software);
	}

	public void removeSoftware(int id) {
		softwareDao.removeSoftware(id);
	}

	public Software getSoftwareByid(int id) {
		Software software=softwareDao.getSoftwareByid(id);
		return software;
	}
	/**
	 * 获取所有软件版本
	 * @return
	 */
	public List<Software> getAllSoftwares() {
		List<Software> softwares=softwareDao.getAllSoftwares();
		return softwares;
	}
	/**
	 * 分页获取版本信息
	 */
	public List<Software> getSoftwaresByPage(int software_type,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		Software software=new Software();
		software.setSoftware_type(software_type);
		parameter.put("page", page);
		parameter.put("software", software);
		return softwareDao.getSoftwaresByPage(parameter);
	}
	/**
	 * 获取最新版本的应用
	 */
	public Software getLastestSoftware(int software_type){
		
		List<Software> softwares=softwareDao.getSoftwaresBytype(software_type);
		if (null!=softwares && softwares.size()>0) {
			Software software=softwares.get(0);
			return software;
		}else {
			return null;
		}
	}
	
	public int getLastestSoftwareVersion(int software_type){
		
		Software software=getLastestSoftware(software_type);
		if (software!=null) {
			return software.getSoftware_version();
		}else {
			return 0;
		}
		
	}
}
