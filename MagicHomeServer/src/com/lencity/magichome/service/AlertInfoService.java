package com.lencity.magichome.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lencity.magichome.dao.AlertInfoDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.AlertInfo;

public class AlertInfoService {
	
	private AlertInfoDao alertInfoDao;
	
	public AlertInfoDao getAlertInfoDao() {
		return alertInfoDao;
	}

	public void setAlertInfoDao(AlertInfoDao alertInfoDao) {
		this.alertInfoDao = alertInfoDao;
	}
	
	public void insertAlertInfo(AlertInfo alertInfo){
		alertInfoDao.insertAlert(alertInfo);
	}

	public List<AlertInfo> getAlertInfos(String account,Date query_time){
		
		Map<String, Object> parameter=new HashMap<String, Object>();
		AlertInfo alertInfo=new AlertInfo();
		alertInfo.setAccount(account);
		parameter.put("alertInfo", alertInfo);
		parameter.put("query_time", query_time);
		List<AlertInfo> alertInfos=alertInfoDao.getAlertInfos(parameter);
		return alertInfos;
		
	}
	
	public List<AlertInfo> getAlertInfosByPage(String account,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		AlertInfo alertInfo=new AlertInfo();
		alertInfo.setAccount(account);
		parameter.put("alertInfo", alertInfo);
		parameter.put("page", page);
		List<AlertInfo> alertInfos=alertInfoDao.getAlertInfosByPage(parameter);
		return alertInfos;
	}
	
	public void deleteAlertInfos(String account){
		alertInfoDao.deleteByAccount(account);
	}
	
	public void deleteAuto(Date begin_time){
		
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		begin_time=calendar.getTime();
		alertInfoDao.deleteAuto(begin_time);
	}
}
