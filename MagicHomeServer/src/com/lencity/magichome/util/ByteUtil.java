package com.lencity.magichome.util;

public class ByteUtil
{
	public static String encode(byte[] bytes)
	{
		String hexString = "0123456789ABCDEF";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++)
		{
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}
	
	private static byte uniteBytes(byte src0, byte src1)
	{
		byte byte0 = Byte.decode("0x" + new String(new byte[]
		{ src0 })).byteValue();
		byte0 = (byte) (byte0 << 4);
		byte byte1 = Byte.decode("0x" + new String(new byte[]
		{ src1 })).byteValue();
		byte ret = (byte) (byte0 | byte1);
		return ret;
	}
	
	/** 将16进制数的字符串转成byte数组 */
	public static byte[] decode(String src)
	{
		int DataLenth = src.length() / 2;
		byte[] ret = new byte[DataLenth];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < DataLenth; ++i)
		{
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	
	// 将大段int值转换成小端int值
	public static int transformSmallEndian(int i)
	{
		return (i & 0xFF) << 24 | (0xFF & i >> 8) << 16 | (0xFF & i >> 16) << 8 | (0xFF & i >> 24);
	}
	
	// 将大段int值转换成小端int的byte数组
	public static byte[] intToSmallEndianByte(int i)
	{
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (0xff & i);
		bytes[1] = (byte) ((0xff00 & i) >> 8);
		bytes[2] = (byte) ((0xff0000 & i) >> 16);
		bytes[3] = (byte) ((0xff000000 & i) >> 24);
		return bytes;
	}
	
	public static byte[] shortToSmallEndianByte(int s)
	{
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (0xff & s);
		bytes[1] = (byte) ((0xff00 & s) >> 8);
		return bytes;
	}
	
	/** 小端模式 * */
	public static int byteToInt(byte[] bytes)
	{
		return ((bytes[1] << 8) | bytes[0]) & 0xFFFF;
	}
	
	 /**
     * 从一个byte[]数组中截取一部分
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i=begin; i<begin+count; i++) bs[i-begin] = src[i];
        return bs;
    }
    
  //byte数组转成long 
    public static long byteToLong(byte[] b) { 
        long s = 0; 
        long s0 = b[0] & 0xff;// 最低位 
        long s1 = b[1] & 0xff; 
        long s2 = b[2] & 0xff; 
        long s3 = b[3] & 0xff; 
        long s4 = b[4] & 0xff;// 最低位 
        long s5 = b[5] & 0xff; 
        long s6 = b[6] & 0xff; 
        long s7 = b[7] & 0xff; 
 
        // s0不变 
        s1 <<= 8; 
        s2 <<= 16; 
        s3 <<= 24; 
        s4 <<= 8 * 4; 
        s5 <<= 8 * 5; 
        s6 <<= 8 * 6; 
        s7 <<= 8 * 7; 
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7; 
        return s; 
    } 
}
