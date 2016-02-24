package com.lencity.magichome.action;

import java.util.List;

import com.lencity.magichome.model.Device;
import com.lencity.magichome.model.Domain;
import com.lencity.magichome.service.DeviceService;
import com.lencity.magichome.service.DomainService;

@SuppressWarnings("serial")
public class DomainManagerAction extends BaseAction{
	
	private DomainService domainService;
	
	private DeviceService deviceService;
	
	private int id;
	
	private String domain_name;
	
	private String new_domain;
	
	private String sn_code;
	
	private List<Domain> domains;

	public DomainService getDomainService() {
		return domainService;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getNew_domain() {
		return new_domain;
	}

	public void setNew_domain(String new_domain) {
		this.new_domain = new_domain;
	}

	public String getSn_code() {
		return sn_code;
	}

	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}
	
	public String list(){
		
		init();
		domains=domainService.getDomainsByPage(domain_name,sn_code,page);
		return "list";
	}
	
	public String add(){
		
		Domain domain=new Domain();
		domain.setDomain_name(domain_name);
		domain.setSn_code(sn_code);
		domainService.insertDomain(domain);
		init();
		domains=domainService.getDomainsByPage(domain_name,sn_code,page);
		addLog("新增了域名"+domain_name);
		return "listAction";
	}
	
	/**
	 * 修改域名
	 */
	public String changeDomain(){
		
		addLog("修改了SN码"+sn_code+"的域名");
		String old_domain=domainService.getDomainBySN(sn_code).getDomain_name();
		Device device = deviceService.getDeviceByDomain(old_domain);
		if (device!=null) {
			deviceService.changeDomain(old_domain, new_domain);
		}
		domainService.changeDomain(sn_code, new_domain);
		init();
		domains=domainService.getDomainsByPage(domain_name,sn_code,page);
		return "list";
	}
	
	/**
	 * 删除域名
	 */
	public String remove(){
		
		Domain domain=new Domain();
		domain=domainService.getDomainByid(id);
		addLog("删除了域名:"+domain.getDomain_name());
		domainService.removeDomain(id);
		init();
		domains=domainService.getDomainsByPage(domain_name,sn_code,page);
		return "list";
	}
}
