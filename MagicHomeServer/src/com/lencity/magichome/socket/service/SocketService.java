package com.lencity.magichome.socket.service;

import com.lencity.magichome.model.SocketParameter;

public class SocketService
{

	public static void procSocketReq(String reqMessage)
	{
		SocketParameter socketParameter = new SocketParameter(reqMessage);
	}

	public static void regist(SocketParameter socketParameter)
	{
		
	}

}
