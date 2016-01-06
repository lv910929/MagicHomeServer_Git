package com.lencity.magichome.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.AlertInfo;

public interface AlertInfoDao {
	
	public void insertAlert(AlertInfo alertInfo);
	
	public List<AlertInfo> getAlertInfos(Map<String,Object> parameter);
	
	public List<AlertInfo> getAlertInfosByPage(Map<String,Object> parameter);
	
	public void deleteByAccount(String account);
	
	public void deleteAuto(Date begin_time);

}
