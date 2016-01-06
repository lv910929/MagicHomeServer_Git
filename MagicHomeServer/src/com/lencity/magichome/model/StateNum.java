package com.lencity.magichome.model;

public class StateNum {
	
	private Integer deviceStatus; //1：在线；2：离线
	
	private Integer deviceNum;

	public StateNum() {
		super();
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Integer getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(Integer deviceNum) {
		this.deviceNum = deviceNum;
	}

}
