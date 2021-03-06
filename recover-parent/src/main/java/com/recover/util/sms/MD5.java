package com.recover.util.sms;

import java.security.MessageDigest;

/**
 * MD5加密
 *@author xiashitao
 *@date 2017年11月2日 下午2:34:02 
 *@version 1.0
 */
public class MD5 {
	public static String MD5Encode(String origin) {
		String ret = null;
		try {
			ret = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			ret = byteArrayToHexString(md.digest(ret.getBytes()));
		} catch (Exception e) {
		}
		return ret;
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
