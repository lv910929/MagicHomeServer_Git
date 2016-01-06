package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Software;

public interface SoftwareDao {
	
	public void insertSoftware(Software software);
	
	public void removeSoftware(int id);
	
	public Software getSoftwareByid(int id);
	
	public List<Software> getAllSoftwares();
	
	public List<Software> getSoftwaresByPage(Map<String, Object> parameter);
	
	public List<Software> getSoftwaresBytype(int software_type);

}
