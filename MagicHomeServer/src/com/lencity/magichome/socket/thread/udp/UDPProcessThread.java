package com.lencity.magichome.socket.thread.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lencity.magichome.util.ByteUtil;

public class UDPProcessThread extends Thread {

	private Executor executor;
	private DatagramSocket udpServer;
	private boolean continueFlag; 
	
	public UDPProcessThread(DatagramSocket udpSocket) {
		executor = Executors.newCachedThreadPool();
		this.udpServer = udpSocket;
		this.continueFlag = true;
		start();
	}

	@Override
	public void run() {
		byte[] buffer = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(buffer, 1024);
		while(continueFlag) {
			try {
				udpServer.receive(receivePacket);
				String clientIP = receivePacket.getAddress().getHostAddress();//中控IP
				byte[] receiveData=Arrays.copyOf(buffer, receivePacket.getLength());
				if (isHeartData(receiveData)) {
					executor.execute(new HeartDataProcessThread(receivePacket.getSocketAddress(),receiveData,clientIP));
				}else {
					executor.execute(new OrderUDPProcessThread(receivePacket.getSocketAddress(),receiveData));
				}
				Thread.sleep(300);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isHeartData(byte[] receiveData) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(receiveData));
		return dis.readInt() < 0;
	}
	
	private class HeartDataProcessThread extends Thread {
		
		private SocketAddress socketAddress;
		private byte[] heartData;
		private String clientIP;
		
		public HeartDataProcessThread(SocketAddress socketAddress,byte[] heartData,String clientIP) {
			
			this.socketAddress = socketAddress;
			this.heartData = heartData;
			this.clientIP = clientIP;
		}

		@Override
		public void run() {
			ByteArrayInputStream bis = new ByteArrayInputStream(heartData);
			DataInputStream dis = new DataInputStream(bis);
			
			try {
				int dataLength= Math.abs((dis.readInt()));
				if (dataLength>0) {
					byte[] macAddressData=new byte[6];
					dis.read(macAddressData);
					String macAddress = ByteUtil.encode(macAddressData);
					UDPBusinessService.serverAddresses.put(macAddress, socketAddress);
					UDPBusinessService.clientIPMap.put(macAddress, clientIP);
					Date date = new Date();
					UDPBusinessService.serverLives.put(macAddress, date);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private class OrderUDPProcessThread extends Thread {

		private SocketAddress socketAddress;
		private byte[] orderData;

		public OrderUDPProcessThread(SocketAddress socketAddress,
				byte[] orderData) {
			this.socketAddress = socketAddress;
			this.orderData = orderData;
		}

		@Override
		public void run() {
			
			// 从orderData获取macAddress和时间戳
			ByteArrayInputStream bis = new ByteArrayInputStream(orderData);
			DataInputStream dis = new DataInputStream(bis);
			String macAddress = "";
			Long timestamp = 0l;
			
			try {
				int dataLength = dis.readInt();
				if (dataLength>0) {
					byte[] macAddressData=new byte[6];
					dis.read(macAddressData);
					macAddress = ByteUtil.encode(macAddressData);
					timestamp=dis.readLong();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			SocketAddress clientAddress = UDPBusinessService.clientAddresses
					.get(timestamp);
			if (clientAddress == null) {
				UDPBusinessService.clientAddresses.put(timestamp, socketAddress);
				SocketAddress serverAddress = UDPBusinessService.serverAddresses
						.get(macAddress);
				if(null!=serverAddress) {
					try {
						DatagramPacket sendOrderPacket = new DatagramPacket(
								orderData, orderData.length, serverAddress);
						udpServer.send(sendOrderPacket);
					} catch (SocketException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				UDPBusinessService.clientAddresses.remove(timestamp);
				DatagramPacket sendOrderPacket;
				try {
					sendOrderPacket = new DatagramPacket(orderData,
							orderData.length, clientAddress);
					udpServer.send(sendOrderPacket);
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
