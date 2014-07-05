package com.kun.flow.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ByteUtil {

	/**
	 * 获取unicode编码byte数组，小端
	 * 
	 * @author songkun
	 * @date Sep 26, 2010 5:13:16 PM
	 * @return byte[]
	 * @param str
	 * @return
	 */
	public static byte[] stringToUnicodeLittleUnmarkedBytes(String str) {
		try {
			return str.getBytes("UnicodeLittleUnmarked");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new byte[0];
	}
	/**
	 * 获取unicode编码byte数组，大端
	 * 
	 * @author songkun
	 * @date Mar 22, 2011 11:48:32 AM
	 * @return byte[]
	 * @param str
	 * @return
	 */
	public static byte[] stringToUnicodeBigUnmarkedBytes(String str) {
		try {
			return str.getBytes("UnicodeBigUnmarked");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * ascii 编码的byte数组转换成字符串
	 * 
	 * @author songkun
	 * @date Dec 6, 2010 9:07:12 PM
	 * @return String
	 * @param dis
	 * @return
	 */
	public static String asciiBytesToString(DataInputStream dis) {
		try {
			int tmpInt = dis.readByte();
			byte[] bytes = new byte[tmpInt];
			dis.read(bytes);
			return new String(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
