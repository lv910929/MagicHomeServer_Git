package com.lencity.magichome.socket.thread.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;


public class SocketServerThread extends Thread
{

	private Executor executor;

	private ServletContext servletContext;

	private Integer socketPort;

	public SocketServerThread(ServletContext servletContext, Integer socketPort)
	{
		executor = Executors.newCachedThreadPool();
		this.servletContext = servletContext;
		this.socketPort = socketPort;
	}

	@Override
	public void run()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(socketPort);
			Socket socket = null;
			while (true)
			{
				socket = serverSocket.accept();
				executor.execute(new SocketProcessThread(servletContext,
						executor, socket));
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
