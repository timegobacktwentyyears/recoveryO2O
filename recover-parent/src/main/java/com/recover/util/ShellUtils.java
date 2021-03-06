package com.recover.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 *@author xiashitao
 *@date 2017年11月2日 下午2:31:30 
 *@version 1.0
 */
public class ShellUtils {
	
	//private static Logger log = LoggerFactory.getLogger(ShellUtils.class);

	public static String exec(String cmd, String charset) throws Exception {
		Runtime rn = Runtime.getRuntime();
		Process p = rn.exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), charset));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

	public static String exec(String[] cmd, String charset) throws Exception {
		Runtime rn = Runtime.getRuntime();
		Process p = rn.exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), charset));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

}