package com.lencity.magichome.socket.thread.tcp;

import java.util.Calendar;
import org.springframework.web.context.WebApplicationContext;
import com.lencity.magichome.model.Device;
import com.lencity.magichome.service.DeviceService;

/**
 * 检查在线设备是否有效
 */
public class CheckDeviceThread extends Thread {

	private WebApplicationContext beanFactory;

	private String macAddress;

	public CheckDeviceThread(WebApplicationContext beanFactory,
			String macAddress) {
		this.beanFactory = beanFactory;
		this.macAddress = macAddress;
	}

	@Override
	public void run() {
		DeviceService deviceService = (DeviceService) beanFactory
				.getBean("deviceService");
		Device device = deviceService.getDeviceByMacAddress(macAddress);
		Calendar communicateTime = Calendar.getInstance();
		communicateTime.setTime(device.getUpdate_time());
		Calendar curTime = Calendar.getInstance();
		curTime.add(Calendar.HOUR, -2);
		// 如果得出时间在当前时间之前，则说明中控未按时与服务端进行通信
		if (communicateTime.before(curTime)) {
			device.setDevice_status(Device.OFF_LINE);
			deviceService.updateDevice(device);
		}
	}
}
