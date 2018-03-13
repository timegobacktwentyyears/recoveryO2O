package com.recover.common;

/**
 * 返回的JSon格式
 *@author xiashitao
 *@date 2017年11月2日 上午10:29:24 
 *@version 1.0
 */
public class ResMsg {

	public static final String CODE_PARAMETER_ERROR = "CODE_PARAMETER_ERROR";//参数错误
	public static final String CODE_SUCCESS = "CODE_SUCCESS";//操作成功
	public static final String CODE_TOKEN_NOTFOUND = "CODE_TOKEN_NOTFOUND";//没有找到token
	public static final String CODE_OTHER_EXCEPTION = "CODE_OTHER_EXCEPTION";//其他异常
	
	private boolean success = true;//true:正常，false:异常
	private String code;
	private String msg;
	private String exceptionType;
	private Object responseObject;
	
	public ResMsg() {
	}
	
	public ResMsg(boolean success) {
		this.success = success;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
	public static ResMsg success() {
		ResMsg res = new ResMsg();
		res.setCode(CODE_SUCCESS);
		return res;
	}
	
	public static ResMsg fail(String code) {
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setCode(code);
		res.setMsg("");
		return res;
	}
	
	public static ResMsg fail(String code, String msg) {
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setCode(code);
		res.setMsg(msg);
		return res;
	}
	
	public static ResMsg success(Object responseObject) {
		ResMsg res = new ResMsg();
		res.setCode(CODE_SUCCESS);
		res.setResponseObject(responseObject);
		return res;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

}