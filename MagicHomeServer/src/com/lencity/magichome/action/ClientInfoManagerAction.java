package com.lencity.magichome.action;

import java.util.List;

import com.lencity.magichome.model.ClientInfo;
import com.lencity.magichome.service.ClientInfoService;

@SuppressWarnings("serial")
public class ClientInfoManagerAction extends BaseAction{
	
	private ClientInfoService clientInfoService;

	public ClientInfoService getClientInfoService() {
		return clientInfoService;
	}

	public void setClientInfoService(ClientInfoService clientInfoService) {
		this.clientInfoService = clientInfoService;
	}
	
	private ClientInfo clientInfo;
	
	private List<ClientInfo> clientInfos;
	
	private String macAddress;
	
	private String ipAddress;
	
	private String cityName;

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public List<ClientInfo> getClientInfos() {
		return clientInfos;
	}

	public void setClientInfos(List<ClientInfo> clientInfos) {
		this.clientInfos = clientInfos;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String list(){
		init();
		clientInfos=clientInfoService.getClientInfosByPage(page, cityName);
		return "list";
	}
	
	public String remove(){
		
		addLog("删除了中控数据:"+macAddress);
		clientInfoService.deleteByMac(macAddress);
		init();
		clientInfos=clientInfoService.getClientInfosByPage(page, cityName);
		return "list";
	}

}
