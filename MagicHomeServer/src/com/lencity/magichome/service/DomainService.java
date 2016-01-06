package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.DomainDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Domain;

public class DomainService {
	
	private DomainDao domainDao;

	public DomainDao getDomainDao() {
		return domainDao;
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}
	
	public void insertDomain(Domain domain){
		domainDao.insertDomain(domain);
	}
	
	public void editDomain(Domain domain){
		domainDao.editDomain(domain);
	}
	
	public void removeDomain(int id){
		domainDao.removeDomain(id);
	}
	
	public Domain getDomainByid(int id){
		Domain domain=domainDao.getDomainByid(id);
		return domain;
	}
	
	public List<Domain> getDomainsByPage(String domain_name,String sn_code,Page page){
		
		Map<String, Object> parameter=new HashMap<String, Object>();
		Domain domain=new Domain();
		domain.setDomain_name(domain_name);
		domain.setSn_code(sn_code);
		parameter.put("domain", domain);
		parameter.put("page", page);
		return domainDao.getDomainsByPage(parameter);
	}
	
	public Domain getDomainBySN(String sn_code){
		return domainDao.getDomainBySN(sn_code);
	}
	
	public Domain getDomainByDomain(String domain_name){
		return domainDao.getDomainByDomain(domain_name);
	}
	
	public void changeDomain(String sn_code,String new_domain){
		
		Domain domain=domainDao.getDomainBySN(sn_code);
		domain.setDomain_name(new_domain);
		domainDao.editDomain(domain);
	}

}
