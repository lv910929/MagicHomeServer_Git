package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Domain;

public interface DomainDao {
	
	public void insertDomain(Domain domain);
	
	public void editDomain(Domain domain);
	
	public void removeDomain(int id);
	
	public Domain getDomainByid(int id);
	
	public Domain getDomainBySN(String sn_code);
	
	public Domain getDomainByDomain(String domain_name);
	
	public List<Domain> getDomainsByPage(Map<String, Object> parameter);

}
