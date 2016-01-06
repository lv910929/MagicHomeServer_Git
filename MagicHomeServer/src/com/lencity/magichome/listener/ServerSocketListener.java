package com.lencity.magichome.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.lencity.magichome.socket.thread.tcp.SocketServerThread;
import com.lencity.magichome.socket.thread.udp.UDPBusinessService;

public class ServerSocketListener implements ServletContextListener
{

	public void contextDestroyed(ServletContextEvent arg0)
	{

	}

	public void contextInitialized(ServletContextEvent event)
	{
		String socketPort = event.getServletContext().getInitParameter("socketPort");
		new SocketServerThread(event.getServletContext(), Integer.parseInt(socketPort)).start();
		UDPBusinessService.init(event.getServletContext());
		event.getServletContext().log("Socket服务启动");
	}

}
