package com.lencity.magichome.socket.thread.udp;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lencity.magichome.model.Device;
import com.lencity.magichome.service.DeviceService;
import com.lencity.magichome.socket.thread.tcp.CheckDeviceThread;

public class DeviceProcessThread extends Thread {

	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final Charset charset = Charset.forName("GBK");
	
	private final Long time = (3 * 60) * 60 * 1000l;
	
	private static Object keyObject;

	private boolean continueFlag;

	private WebApplicationContext beanFactory;
	
	private Executor executor;

	static {
		keyObject = new Object();
	}

	public DeviceProcessThread(ServletContext servletContext) {
		super();
		beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		executor = Executors.newCachedThreadPool();
		this.continueFlag = true;
		start();
	}

	@Override
	public void run() {

		while (continueFlag) {
			
			try {
				sleep(time);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for (String macAddress : UDPBusinessService.serverLives.keySet()) {
				
				DeviceService deviceService = (DeviceService) beanFactory
						.getBean("deviceService");
				Device device = deviceService.getDeviceByMacAddress(macAddress);
				if (device != null) {
					Date date = UDPBusinessService.serverLives.get(macAddress);
					device.setUpdate_time(date);
					device.setDevice_status(Device.ON_LINE);
					synchronized (keyObject) {
						deviceService.updateDevice(device);
					}
					executor.execute(new CheckDeviceThread(beanFactory, macAddress));
				}
			}
		}
	}

}
