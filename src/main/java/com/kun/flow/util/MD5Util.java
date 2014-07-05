package com.kun.flow.util;

/**
 * MD5转换(实现方式来至网络)
 * 
 * @author：songkun
 * @date Apr 7, 2011-3:30:33 PM
 * @since 2.0.0
 */
public class MD5Util {
	// 用来将字节转换成 16 进制表示的字符
	private static char HexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * 根据源字符串获取MD5字符串
	 * 
	 * @author songkun
	 * @date Dec 4, 2010 1:52:47 PM
	 * @return String
	 * @param src
	 * @return
	 */
	public static final String getMD5String(String src) {
		if (src == null) {
			return null;
		}
		byte[] tmp = src.getBytes();
		String dist = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(tmp);
			// MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			tmp = md.digest();
			// 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			char chars[] = new char[16 * 2];
			int k = 0; // 表示转换结果中对应的字符位置
			// 从第一个字节开始，对 MD5 的每一个字节
			// 转换成 16 进制字符的转换
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i]; // 取第 i 个字节
				chars[k++] = HexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				chars[k++] = HexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			dist = new String(chars); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dist;
	}

	public static void main(String[] args) {
		System.out.println(getMD5String("123456"));
	}
}
