package com.lencity.magichome.socket.thread.udp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lencity.magichome.model.DeviceNum;
import com.lencity.magichome.model.StateNum;
import com.lencity.magichome.service.DeviceNumService;
import com.lencity.magichome.service.DeviceService;

public class StatisDeviceThread extends Thread{
	
	private final Long time = (24 * 60) * 60 * 1000l;
	
	private boolean continueFlag;
	
	private WebApplicationContext beanFactory;
	
	private static Object keyObject;
	
	static {
		keyObject = new Object();
	}

	public StatisDeviceThread(ServletContext servletContext) {
		super();
		beanFactory = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		this.continueFlag = true;
		start();
	}
	
	@Override
	public void run() {
		super.run();
		while (continueFlag) {
			try {
				updateTable();
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateTable(){
		
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		Integer onlineNum = 0;
		Integer offlineNum = 0;
		DeviceService deviceService = (DeviceService) beanFactory.getBean("deviceService");
		List<StateNum> list = deviceService.getNumByOnline();
		for (StateNum stateNum : list) {
			if (stateNum.getDeviceStatus()==1) {
				onlineNum = stateNum.getDeviceNum();
			}else {
				offlineNum = stateNum.getDeviceNum();
			}
		}
		DeviceNum deviceNum = new DeviceNum();
		deviceNum.setWeekdayId(weekday);
		deviceNum.setOnlineNum(onlineNum);
		deviceNum.setOfflineNum(offlineNum);
		DeviceNumService deviceNumService = (DeviceNumService) beanFactory.getBean("deviceNumService");
		synchronized (keyObject) {
			deviceNumService.updateDeviceNum(deviceNum);
		}
		
	}

}
