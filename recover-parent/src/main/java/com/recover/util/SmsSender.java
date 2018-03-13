package com.recover.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

/**
 * 发送短信
 *@author xiashitao
 *@date 2017年11月2日 下午2:32:36 
 *@version 1.0
 */
public class SmsSender {
	
	private static Logger log = LoggerFactory.getLogger(SmsSender.class);

	//"{\"customer\":\"abc\",\"code\":\"123\"}"
	public static String send(String accessKeyId, String secret, String templateCode, String paramString, String recNum) {
		String requestId = null;
		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName("医智捷");// 控制台创建的签名名称
			request.setTemplateCode(templateCode);// 控制台创建的模板CODE
			request.setParamString(paramString);// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setRecNum(recNum);// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			requestId = httpResponse.getRequestId();
		} catch (ServerException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return requestId;
	}
	
}
