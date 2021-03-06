package com.recover.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5676503347200392663L;
	private String code;
	private String msg;
	
	public BusinessException(String code, String msg) {
		super(code+":"+msg);
		this.code = code;
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return code+":"+msg;
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
	
	public String toString() {
		return "code="+code+",msg="+msg;
	}
	
}