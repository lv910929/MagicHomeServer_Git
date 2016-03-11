package com.lencity.magichome.socket.thread.udp;


public class CleanMapThread extends Thread {
	
	private final Long time = (12 * 60) * 60 * 1000l;
	
	private boolean continueFlag;

	public CleanMapThread() {
		this.continueFlag = true;
		start();
	}
	
	@Override
	public void run() {
		super.run();
		while (continueFlag) {
			try {
				sleep(time);
				UDPBusinessService.clientAddresses.clear();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
