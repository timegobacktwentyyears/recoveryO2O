package com.joinstar.util.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *@author xiashitao
 *@date 2017年11月2日 下午2:34:22 
 *@version 1.0
 */
public class ZykjSmsApi {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String s = send(Account.url, Account.sid, MD5.MD5Encode(Account.cpid + Account.key), "18072753512", "test111", "");
		System.out.println(s);
	}
	
	private static void chkPoint() throws Exception {
		Map<String, String> pars = new HashMap<String, String>();
		//pars.put("cpid", Account.cpid);
		pars.put("sid", Account.sid);
		pars.put("sign", MD5.MD5Encode(Account.cpid + Account.key));
		String result = doGet(Account.url+"/chkPoint.action", pars);
	}
	
	public static String send(String url, String username, String password, String mobile, String content, String spCode) throws Exception {
		Map<String, String> pars = new HashMap<String, String>();
		pars.put("sid", username);
		pars.put("sign", password);
		pars.put("mobi", mobile);
		pars.put("msg", encode_get(content));
		pars.put("spcode", spCode);
		String result = doGet(url+"/sendSms.action", pars);
		return result;
	}

	private static String encode_get(String msg) throws Exception {
		byte[] b = Base64.encodeBase64(msg.getBytes("UTF-8"));
		msg = new String(b, "UTF-8");
		String s = java.net.URLEncoder.encode(msg, "UTF-8");
		return s;
	}
	
	private static String doGet(String urlStr, Map<String, String> paramMap)
			throws Exception {
		String paramStr = prepareParam(paramMap);
		if (paramStr == null || paramStr.trim().length() < 1) {

		} else {
			urlStr += "?" + paramStr;
		}
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
		conn.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line;
		String result = "";
		while ((line = br.readLine()) != null) {
			result += line;
		}
		br.close();
		return result;
	}

	private static String prepareParam(Map<String, String> paramMap) {
		StringBuffer sb = new StringBuffer();
		if (paramMap.isEmpty()) {
			return "";
		} else {
			for (String key : paramMap.keySet()) {
				String value = (String) paramMap.get(key);
				if (sb.length() < 1) {
					sb.append(key).append("=").append(value);
				} else {
					sb.append("&").append(key).append("=").append(value);
				}
			}
			return sb.toString();
		}
	}

}
