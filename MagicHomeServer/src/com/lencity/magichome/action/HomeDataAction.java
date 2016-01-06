package com.lencity.magichome.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lencity.magichome.model.DeviceNum;
import com.lencity.magichome.service.ClientInfoService;
import com.lencity.magichome.service.DeviceNumService;
import com.lencity.magichome.service.DeviceService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomeDataAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	HttpServletRequest request;

	HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private ClientInfoService clientInfoService;

	public ClientInfoService getClientInfoService() {
		return clientInfoService;
	}

	public void setClientInfoService(ClientInfoService clientInfoService) {
		this.clientInfoService = clientInfoService;
	}
	
	private DeviceNumService deviceNumService;

	public DeviceNumService getDeviceNumService() {
		return deviceNumService;
	}

	public void setDeviceNumService(DeviceNumService deviceNumService) {
		this.deviceNumService = deviceNumService;
	}

	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	//统计中控ip归属地
	public void countCity(){
		
		try {
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			
			PrintWriter pw = response.getWriter();
			Gson gson = new GsonBuilder().create();
			List<Map<String, Object>> list=clientInfoService.getCountByCity();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list); 
			String json=gson.toJson(map);
			pw.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//统计中控在线数量
	public void countDevice(){
		
		try {
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			
			PrintWriter pw = response.getWriter();
			Gson gson = new GsonBuilder().create();
			List<DeviceNum> list=deviceNumService.getDeviceNums();
			List<Integer> onlineList = new ArrayList<Integer>();
			List<Integer> offlineList = new ArrayList<Integer>();
			for (DeviceNum deviceNum : list) {
				onlineList.add(deviceNum.getOnlineNum());
				offlineList.add(deviceNum.getOfflineNum());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("onlineList", onlineList); 
			map.put("offlineList", offlineList); 
			String json=gson.toJson(map);
			pw.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
