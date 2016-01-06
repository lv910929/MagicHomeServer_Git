package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.ClientInfo;

public interface ClientInfoDao {
	
	public void insertClientInfo(ClientInfo clientInfo);
	
	public List<ClientInfo> getAllClientInfos();
	
	public List<Map<String, Object>> getCountByCity();
	
	public List<ClientInfo> getClientInfosByPage(Map<String,Object> parameter);
	
	public ClientInfo getClientInfoByMac(String macAddress);
	
	public void updateClientInfo(ClientInfo clientInfo);
	
	public void deleteByMac(String macAddress);

}
