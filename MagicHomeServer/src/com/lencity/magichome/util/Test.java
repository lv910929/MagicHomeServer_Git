package com.lencity.magichome.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONException;

public class Test {
	
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
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
	        connection.setRequestProperty("apikey",  "b67c2167f242ca3c8fb1ea7000772f2d");
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
	
	public static void main(String[] args) {
		
		String httpUrl = "http://apis.baidu.com/3023/ip/ip";
		String httpArg = "ip=222.188.209.50";
		String jsonResult = request(httpUrl, httpArg);
		System.out.println(jsonResult);
		try {
			Map<String,Object> map = JsonHelper.toMap(jsonResult);
			String province = (String) map.get("province");
			String city = (String) map.get("city");
			System.out.println(province+city);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
