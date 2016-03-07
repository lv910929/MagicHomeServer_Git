package com.lencity.magichome.socket.thread.udp;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class UDPBusinessService {

	private static final int HEART_UDP_PORT = 12000;
	
	private static DatagramSocket udpServer;
	
	public static Map<String,SocketAddress> serverAddresses;
	public static Map<String,Date> serverLives;
	public static Map<Long,SocketAddress> clientAddresses;
	public static Map<String, String> clientIPMap;
	
	public static void init(ServletContext servletContext){
		try {
			udpServer = new DatagramSocket(HEART_UDP_PORT);
			serverAddresses = Collections.synchronizedMap(new HashMap<String,SocketAddress>());
			serverLives = Collections.synchronizedMap(new HashMap<String,Date>());
			clientAddresses = new HashMap<Long,SocketAddress>();
			clientIPMap = new HashMap<String, String>();
			
			new UDPProcessThread(udpServer);
			new DeviceProcessThread(servletContext);
			new ClientIpProcessThread(servletContext);
			new StatisDeviceThread(servletContext);
			new AlertCleanThread(servletContext);
			new CleanMapThread();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
