package com.lencity.magichome.socket.thread.udp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;

import org.json.JSONException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lencity.magichome.model.ClientInfo;
import com.lencity.magichome.service.ClientInfoService;
import com.lencity.magichome.util.JsonHelper;

public class ClientIpProcessThread extends Thread {

	private final static String httpUrl = "http://apis.baidu.com/apistore/iplookupservice/iplookup";
	
	private final static String apiKey = "b67c2167f242ca3c8fb1ea7000772f2d";
	
	private final Long time = (3 * 60) * 60 * 1000l;

	private static Object keyObject;

	private boolean continueFlag;

	private WebApplicationContext beanFactory;

	private Executor executor;

	static {
		keyObject = new Object();
	}

	public ClientIpProcessThread(ServletContext servletContext) {
		super();
		beanFactory = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
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
			for (String macAddress : UDPBusinessService.clientIPMap.keySet()) {
				ClientInfoService clientInfoService = (ClientInfoService) beanFactory
						.getBean("clientInfoService");
				ClientInfo clientInfo = clientInfoService
						.getClientInfoByMac(macAddress);
				String clientIp = UDPBusinessService.clientIPMap
						.get(macAddress);
				String data = "";
				String httpArg = "ip="+clientIp;
				if (clientInfo != null) {
					try {
						data = request(httpUrl, httpArg);
						String addressName = parseJsonData(data);
						clientInfo.setIpAddress(clientIp);
						clientInfo.setCityName(addressName);
						synchronized (keyObject) {
							clientInfoService.updateClientInfo(clientInfo);
						}
					} catch (Exception e) {
						System.out.println("json解析出错");
						continue;
					} finally {
						continueFlag = true;
					}
				} else {
					try {
						data = request(httpUrl, httpArg);
						String addressName = parseJsonData(data);
						clientInfo=new ClientInfo();
						clientInfo.setMacAddress(macAddress);
						clientInfo.setIpAddress(clientIp);
						clientInfo.setCityName(addressName);
						synchronized (keyObject) {
							clientInfoService.insertClientInfo(clientInfo);
						}
					} catch (Exception e) {
						System.out.println("json解析出错");
						continue;
					} finally {
						continueFlag = true;
					}
				}
			}
		}
	}
	
	/**
	 * @param urlAll :请求接口
	 * @param httpArg :参数
	 * @return 返回结果
	 */
	private String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  apiKey);
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	private String parseJsonData(String JsonData) {

		String addressName = "";
		try {
			Map<String, Object> jsonMap = JsonHelper.toMap(JsonData);
			String retData = (String) jsonMap.get("retData");
			Map<String, Object> addressMap = JsonHelper.toMap(retData);
			String province = (String) addressMap.get("province");
			String city = (String) addressMap.get("city");
			addressName = province + city;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return addressName;
	}
}
