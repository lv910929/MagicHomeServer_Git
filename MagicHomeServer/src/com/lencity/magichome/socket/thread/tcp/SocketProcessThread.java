package com.lencity.magichome.socket.thread.tcp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.lencity.magichome.model.Account;
import com.lencity.magichome.model.AlertInfo;
import com.lencity.magichome.model.Device;
import com.lencity.magichome.model.Domain;
import com.lencity.magichome.model.Feedback;
import com.lencity.magichome.model.Hardware;
import com.lencity.magichome.model.Software;
import com.lencity.magichome.service.AccountService;
import com.lencity.magichome.service.AlertInfoService;
import com.lencity.magichome.service.DeviceService;
import com.lencity.magichome.service.DomainService;
import com.lencity.magichome.service.FeedbackService;
import com.lencity.magichome.service.HardwareService;
import com.lencity.magichome.service.SoftwareService;
import com.lencity.magichome.util.ByteUtil;

public class SocketProcessThread extends Thread {

	private static Object keyObject;

	private static final SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final Charset charset = Charset.forName("GBK");

	private static final int timeToLive = 60 * 60 * 24;

	private static byte[] bufferData = new byte[1024];

	private WebApplicationContext beanFactory;

	private Executor executor;

	private Socket socket;

	private DataInputStream dis;

	private OutputStream out;

	private JPushClient alertInfoPush;

	private int sendNo;

	static {
		keyObject = new Object();
	}

	public SocketProcessThread(ServletContext servletContext,
			Executor executor, Socket socket) {
		beanFactory = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		this.executor = executor;
		this.socket = socket;
		alertInfoPush = new JPushClient("a8a660829440e5c837ad2d21",
				"a251eafceb28fd5fed22cbc7",true,timeToLive);
		sendNo = 1;
	}

	public String[] checkValidate(int code,byte[] requestData) {

		ByteArrayInputStream bis = new ByteArrayInputStream(requestData);
		DataInputStream dis = new DataInputStream(bis);

		String[] requestStrings = null;
		try {
			int dataNum = dis.readInt();
			requestStrings = new String[dataNum];
			int[] requestLengthStrings = new int[dataNum];
			int first = 0;
			for (int i = 0; i < requestLengthStrings.length; i++) {
				requestLengthStrings[i] = dis.readInt();
				first = first | requestLengthStrings[i];
			}
			for (int i = 0; i < requestLengthStrings.length; i++) {
				if (requestLengthStrings[i] > 0) {
					byte[] temData = new byte[requestLengthStrings[i]];
					int length = dis.read(temData);
					if (length > 0) {
						if (code==100) {
							requestStrings[i] =ByteUtil.encode(temData);
						}else {
							requestStrings[i] = new String(temData, charset);
						}
					}
				}
			}
			int checkCode = dis.readInt();
			if (first != checkCode) {
				requestStrings = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return requestStrings;
	}

	public byte[] dataBufferMemory(DataInputStream dis, int responseDataLength) {

		byte[] responseData = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			while (responseDataLength > 0) {
				int length = dis.read(bufferData);
				if (length > 0) {
					bos.write(bufferData, 0, length);
					responseDataLength -= length;
				}
			}
			responseData = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseData;
	}

	@Override
	public void run() {

		try {
			dis = new DataInputStream(socket.getInputStream());
			out = socket.getOutputStream();
			byte[] result = null;
			int responseDataLength = dis.readInt() - 4;
			if (responseDataLength > 0) {
				int code = dis.readInt();// 命令字
				byte[] responseData = dataBufferMemory(dis, responseDataLength);
				String[] parameters = checkValidate(code,responseData);
				if (parameters != null) {

					switch (code) {
					case 1:
						result = registDevice(parameters);
						break;
					case 2:
						result = login(parameters);
						break;
					case 3:
						//修改花生棒域名端口
						result = changeDomainPort(parameters);
						break;
					case 4:
						result = changePassword(parameters);
						break;
					case 5:
						result = getLatestVersion(parameters);
						break;
					case 6:
						result = getApkUrl(parameters);
						break;
					case 7:
						result = getAlertInfos(parameters);
						break;
					case 8:
						result = getHardwareVersion(parameters);
						break;
					case 9:
						// 添加用户反馈
						result = addFeedback(parameters);
						break;
					case 10:
						result = getModuleDeviceVersion(parameters);
						break;
					case 100:
						// 简化版消息推送
						result = pushAlertInfo(parameters);
						break;
					}
					if (result != null) {
						out.write(result);
					}
				}
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] registDevice(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		String domainString = "";
		byte result = 0;
		try {
			String accountName = parameters[0];
			String password = parameters[1];
			String mobile = parameters[2];
			String macAddress = parameters[3];
			String SNCode = parameters[4];
			AccountService accountService = (AccountService) beanFactory
					.getBean("accountService");
			DomainService domainService = (DomainService) beanFactory
					.getBean("domainService");
			Domain domain = domainService.getDomainBySN(SNCode);
			DeviceService deviceService = (DeviceService) beanFactory
					.getBean("deviceService");
			Device device = deviceService.getDeviceByMacAddress(macAddress);
			if ((!accountService.isExist(accountName)) && (domain != null)
					&& (device == null)) {
				Device tmpDevice = deviceService.getDeviceByDomain(domain
						.getDomain_name());
				if (tmpDevice != null) {
					result = 3;// 中控已被注册
				} else {

					Account account = new Account();
					account.setAccountName(accountName);
					account.setPassword(password);
					account.setMobile(mobile);
					synchronized (keyObject) {
						accountService.insertAccount(account);
					}
					domainString = domain.getDomain_name();

					device = new Device();
					device.setAccount_name(accountName);
					device.setMac_address(macAddress);
					device.setDomain_name(domainString);
					device.setSn_code(SNCode);
					device.setDevice_status(Device.OFF_LINE);
					device.setRegister_time(new Date());
					synchronized (keyObject) {
						deviceService.insertDevice(device);
					}
					result = 0;// 注册成功
				}

			} else if (accountService.isExist(accountName)) {
				result = 1;// 用户名已存在
			} else if (domain == null) {
				result = 2;// 二维码有误
			} else if (device != null) {
				result = 3;// 中控已被注册
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dos.writeInt(1 + domainString.getBytes(charset).length);
		dos.writeByte(result);
		dos.write(domainString.getBytes(charset));
		return bos.toByteArray();
	}

	public byte[] login(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		String domain = "";
		try {
			String accountName = parameters[0];
			String password = parameters[1];
			AccountService accountService = (AccountService) beanFactory
					.getBean("accountService");
			Account account = accountService.login(accountName, password);
			if (account != null) {
				DeviceService deviceService = (DeviceService) beanFactory
						.getBean("deviceService");
				Device device = deviceService.getDeviceByAccount(accountName);
				if (device != null) {
					result = 0;
					domain = device.getDomain_name();
				}
			} else {
				result = 1;// 账号密码错误
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dos.writeInt(1 + domain.getBytes(charset).length);
		dos.writeByte(result);
		dos.write(domain.getBytes(charset));
		return bos.toByteArray();
	}

	public byte[] changePassword(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		try {
			String accountName = parameters[0];
			String oldPassword = parameters[1];
			String newPassword = parameters[2];
			AccountService accountService = (AccountService) beanFactory
					.getBean("accountService");
			Account account = accountService.login(accountName, oldPassword);
			if (account != null) {
				synchronized (keyObject) {
					account = accountService.changePassword(accountName,
							newPassword);
				}
				result = 0;
			} else {
				result = 1;// 账号密码错误
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		dos.writeInt(1);
		dos.writeByte(result);
		return bos.toByteArray();
	}
	
	public byte[] changeDomainPort(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		try {
			String accountName = parameters[0];
			String password = parameters[1];
			String newDomain = parameters[2];
			String newPort = parameters[3];
			String newDomainName = "";
			AccountService accountService = (AccountService) beanFactory.getBean("accountService");
			Account account = accountService.login(accountName, password);
			if (account != null) {
				DeviceService deviceService = (DeviceService) beanFactory.getBean("deviceService");
				Device device = deviceService.getDeviceByAccount(accountName);
				if (device != null) {
					newDomainName = newDomain+"&"+newPort+"&1500";
					DomainService domainService = (DomainService) beanFactory.getBean("domainService");
					Domain domain = domainService.getDomainByDomain(device.getDomain_name());
					domain.setDomain_name(newDomainName);
					synchronized (keyObject) {
						domainService.editDomain(domain);
					}
					device.setDomain_name(newDomainName);
					synchronized (keyObject) {
						deviceService.updateDevice(device);
					}
				}else {
					result = 2;// 中控设备不存在
				}
			}else {
				result = 1;// 账号或密码错误
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dos.writeInt(1);
		dos.writeByte(result);
		return bos.toByteArray();
	}

	public byte[] getLatestVersion(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		Software software;
		int lastVersion = 0;
		String softwareInfo = "";
		String appType = parameters[0];
		SoftwareService softwareService = (SoftwareService) beanFactory
				.getBean("softwareService");
		software = softwareService.getLastestSoftware(Integer.parseInt(appType));
		if (software != null) {
			result = 0;
			lastVersion = software.getSoftware_version();
			softwareInfo = software.getSoftware_info();
		} else {
			result = 1;
		}
		dos.writeInt(9+softwareInfo.getBytes(charset).length);
		dos.writeByte(result);
		dos.writeInt(lastVersion);
		dos.writeInt(softwareInfo.getBytes(charset).length);
		dos.write(softwareInfo.getBytes(charset));
		return bos.toByteArray();
	}
	
	public byte[] getHardwareVersion(String[] parameters) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		int hardwareVersion = 0;
		String hardware_type = parameters[0];
		HardwareService hardwareService = (HardwareService) beanFactory.getBean("hardwareService");
		Hardware hardware = hardwareService.getHardwareByType(Integer.parseInt(hardware_type));
		if (hardware!=null) {
			hardwareVersion=hardware.getHardware_version();
			result = 0;
		}else {
			result = 1;
		}
		dos.writeInt(5);
		dos.writeByte(result);
		dos.writeInt(hardwareVersion);
		return bos.toByteArray();
	}
	
	//获取最新模块设备表版本
	public byte[] getModuleDeviceVersion(String[] parameters) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		int moduleDeviceVersion = 0;
		String hardware_type = parameters[0];
		HardwareService hardwareService = (HardwareService) beanFactory.getBean("hardwareService");
		Hardware hardware = hardwareService.getHardwareByType(Integer.parseInt(hardware_type));
		if (hardware!=null) {
			moduleDeviceVersion=hardware.getHardware_version();
			result = 0;
		}else {
			result = 1;
		}
		dos.writeInt(5);
		dos.writeByte(result);
		dos.writeInt(moduleDeviceVersion);
		return bos.toByteArray();
	}

	public byte[] getApkUrl(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		String apkUrl = "";
		String software_type = parameters[0];
		SoftwareService softwareService = (SoftwareService) beanFactory
				.getBean("softwareService");
		Software software = softwareService.getLastestSoftware(Integer
				.parseInt(software_type));
		if (null != software) {
			apkUrl = software.getDownload_url();
			result = 0;
		} else {
			result = 1;
		}
		dos.writeInt(5 + apkUrl.getBytes(charset).length);
		dos.writeByte(result);
		dos.writeInt(apkUrl.getBytes(charset).length);
		dos.write(apkUrl.getBytes(charset));
		return bos.toByteArray();
	}

	/**
	 * 获取报警日志
	 */
	public byte[] getAlertInfos(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		StringBuffer dataBuffer = new StringBuffer();
		String dataString = "";
		try {
			String account = parameters[0];
			String query_time = parameters[1];
			if (account != null && (!account.trim().equals(""))) {
				AlertInfoService alertInfoService = (AlertInfoService) beanFactory
						.getBean("alertInfoService");
				List<AlertInfo> alertInfos = new ArrayList<AlertInfo>();
				if (query_time != null) {
					alertInfos = alertInfoService.getAlertInfos(account,
							df.parse(query_time));
				} else {
					alertInfos = alertInfoService.getAlertInfos(account, null);
				}
				if (alertInfos.size() > 0) {
					for (AlertInfo alertInfo : alertInfos) {
						dataBuffer.append(alertInfo.getAlert_info() + "&");
						dataBuffer.append(df.format(alertInfo.getPush_time())
								+ "#");
					}
					result = 0;
					dataString = dataBuffer.substring(0,
							dataBuffer.length() - 1);
				} else {
					result = 1;// 无报警日志
				}
			} else {
				result = 1;// 无报警日志
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String lastQueryTime = df.format(new Date());
		dos.writeInt(5 + lastQueryTime.getBytes().length
				+ dataString.getBytes(charset).length);
		dos.writeByte(result);
		dos.writeInt(lastQueryTime.getBytes().length);
		dos.write(lastQueryTime.getBytes());
		dos.write(dataString.getBytes(charset));
		return bos.toByteArray();
	}

	// 添加用户反馈
	private byte[] addFeedback(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		try {
			String accountName = parameters[0];
			String content = parameters[1];
			FeedbackService feedbackService = (FeedbackService) beanFactory
					.getBean("feedbackService");
			Feedback feedback = new Feedback();
			feedback.setAccount_name(accountName);
			feedback.setContent(content);
			feedback.setCreate_time(new Date());
			feedback.setStatus(1);
			synchronized (keyObject) {
				feedbackService.insertFeedback(feedback);
			}
			result = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		dos.writeInt(1);
		dos.writeByte(result);
		return bos.toByteArray();
	}

	public byte[] pushAlertInfo(String[] parameters) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		byte result = 0;
		String macAddress = parameters[0];
		String deviceCode = parameters[1];
		Date alertTime = new Date();
		DeviceService deviceService = (DeviceService) beanFactory
				.getBean("deviceService");
		Device device = deviceService.getDeviceByMacAddress(macAddress);
		if (device != null) {
			AlertInfoService alertInfoService = (AlertInfoService) beanFactory
					.getBean("alertInfoService");
			AlertInfo alertInfo = new AlertInfo();
			alertInfo.setAccount(device.getAccount_name());
			alertInfo.setAlert_info(deviceCode);
			alertInfo.setPush_time(alertTime);
			synchronized (keyObject) {
				alertInfoService.insertAlertInfo(alertInfo);
			}
			AndroidNotification androidNotification = AndroidNotification
					.newBuilder().setAlert("有新的报警消息")
					.addExtra("deviceCode", deviceCode)
					.addExtra("alertTime", df.format(alertTime)).build();
			IosNotification iosNotification = IosNotification.newBuilder()
					.setContentAvailable(true).setAlert("有新的报警消息")
					.setSound("default").addExtra("deviceCode", deviceCode)
					.addExtra("alertTime", df.format(alertTime)).build();
			PushPayload pushPayload = PushPayload
					.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.alias(macAddress))
					.setNotification(
							Notification
									.newBuilder()
									.addPlatformNotification(androidNotification)
									.addPlatformNotification(iosNotification)
									.build()).build();
			try {
				PushResult pushResult = alertInfoPush.sendPush(pushPayload);
				if (pushResult.isResultOK()) {
					result = 0;
				} else {
					result = 1;
				}
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
		dos.writeInt(1);
		dos.writeByte(result);
		return bos.toByteArray();
	}

}
