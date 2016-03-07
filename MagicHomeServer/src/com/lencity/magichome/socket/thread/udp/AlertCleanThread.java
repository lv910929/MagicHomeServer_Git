package com.lencity.magichome.socket.thread.udp;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lencity.magichome.service.AlertInfoService;

public class AlertCleanThread extends Thread{
	
	private final Long time = (24 * 60) * 60 * 1000l;
	
	private boolean continueFlag;

	private WebApplicationContext beanFactory;
	
	public AlertCleanThread(ServletContext servletContext){
		super();
		beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		this.continueFlag = true;
		start();
	}
	
	@Override
	public void run() {
		super.run();
		while (continueFlag) {
			AlertInfoService alertInfoService = (AlertInfoService) beanFactory.getBean("alertInfoService");
			alertInfoService.deleteAuto();
			System.out.println("正在清理一个月前的报警记录。。。");
			try {
				sleep(time);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
