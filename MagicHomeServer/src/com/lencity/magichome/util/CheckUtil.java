package com.lencity.magichome.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class CheckUtil {
	
	//和校验
	public static String[] andCheck(DataInputStream dis) throws IOException{
		
		int dataNum=dis.readInt();
		String[] requestStrings=new String[dataNum];
		int[] requestLengthStrings=new int[dataNum];
		int checkCode=dis.readInt();
		int first=1024;
		for (int i = 0; i < requestLengthStrings.length; i++) {
			requestLengthStrings[i]=dis.readInt();
			first=first&requestLengthStrings[i];
		}
		if (first==checkCode) {
			for (int i = 0; i < requestLengthStrings.length; i++) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				if (requestLengthStrings[i]>0) {
					byte[] temData = new byte[requestLengthStrings[i]];
					int length = dis.read(temData);
					if (length > 0) {
						bos.write(temData, 0, length);
					}
				}
				requestStrings[i]=new String(bos.toByteArray(),Charset.forName("GBK"));
			}
		}
		
		return requestStrings;
	}

}
