package com.lencity.magichome.model;

public class SocketParameter
{

	private int requestType;

	private String[] parameters;

	public SocketParameter(String message)
	{
		String[] tmp = message.split("@");
		requestType = Integer.parseInt(tmp[0]);
		if (tmp.length > 1)
		{
			parameters = tmp[1].split("#");
		}
	}

	public int getRequestType()
	{
		return requestType;
	}

	public void setRequestType(int requestType)
	{
		this.requestType = requestType;
	}

	public String[] getParameters()
	{
		return parameters;
	}

	public void setParameters(String[] parameters)
	{
		this.parameters = parameters;
	}

}
