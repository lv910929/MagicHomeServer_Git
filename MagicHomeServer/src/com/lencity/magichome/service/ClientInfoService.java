package com.lencity.magichome.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.ClientInfoDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.ClientInfo;

public class ClientInfoService {

	private ClientInfoDao clientInfoDao;

	public ClientInfoDao getClientInfoDao() {
		return clientInfoDao;
	}

	public void setClientInfoDao(ClientInfoDao clientInfoDao) {
		this.clientInfoDao = clientInfoDao;
	}

	public void insertClientInfo(ClientInfo clientInfo) {
		clientInfoDao.insertClientInfo(clientInfo);
	}

	public List<ClientInfo> getAllClientInfos() {
		List<ClientInfo> clientInfos = new ArrayList<ClientInfo>();
		clientInfos = clientInfoDao.getAllClientInfos();
		return clientInfos;
	}

	public List<ClientInfo> getClientInfosByPage(Page page, String cityName) {

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("cityName", cityName);
		parameter.put("page", page);
		return clientInfoDao.getClientInfosByPage(parameter);
	}

	public ClientInfo getClientInfoByMac(String macAddress) {

		ClientInfo clientInfo = clientInfoDao.getClientInfoByMac(macAddress);
		return clientInfo;
	}

	public void updateClientInfo(ClientInfo clientInfo) {
		clientInfoDao.updateClientInfo(clientInfo);
	}

	public void deleteByMac(String macAddress) {
		clientInfoDao.deleteByMac(macAddress);
	}
	
	public List<Map<String, Object>> getCountByCity(){
		
		return clientInfoDao.getCountByCity();
	}
}
