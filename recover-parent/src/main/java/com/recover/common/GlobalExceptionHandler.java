package com.recover.common;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.dubbo.rpc.RpcException;
import com.recover.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private MessageSource messageSource;
	
	public GlobalExceptionHandler() {
	}
	
	//统一处理Service层的自定义异常
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResMsg exceptionHandler(RuntimeException ex, HttpServletRequest request) {
		log.error(ex.getMessage(), ex);
		try {
			String exMsg = ex.getMessage();
			if(exMsg != null) {
				String exceptionInfo = exMsg.substring(0, ex.getMessage().indexOf(System.getProperty("line.separator")));
				String[] arr = exceptionInfo.split(":");
				if(arr[0].equals("com.yizhihui.exception.BusinessException")) {
					String code = arr[1].trim();
					String defaultMsg = arr[2].trim();
					String msg = null;
					log.info("msg>code:"+code+",msg:"+msg);
					ResMsg res = new ResMsg();
					res.setSuccess(false);
					if(code.startsWith("{") && code.endsWith("}")) {
						code = code.substring(1, code.length()-1);
						Locale locale = RequestContextUtils.getLocale(request);//取国家语言信息
						try {
							msg = messageSource.getMessage(code, null, locale);//取消息
						} catch(NoSuchMessageException e) {
							msg = defaultMsg;
						}
						res.setCode(code);
						res.setMsg(msg);
					} else {
						res.setMsg(code);
					}
					return res;
				} else {
					ResMsg res = new ResMsg();
					res.setSuccess(false);
					return res;	
				}
			} else {
				ResMsg res = new ResMsg();
				res.setSuccess(false);
				res.setExceptionType(ex.getClass().getName());//异常类型是原生异常的类型
				return res;
			}
		} catch(Exception ex2) {
			ResMsg res = new ResMsg();
			res.setSuccess(false);
			res.setExceptionType(ex.getClass().getName());//异常类型是原生异常的类型
			return res;
		}
	}


	//处理表单验证异常
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResMsg exceptionHandler(BindException ex) {
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setCode(ResMsg.CODE_PARAMETER_ERROR);
		List<FieldError> list = ex.getFieldErrors();
		StringBuffer sb = new StringBuffer();
		for(FieldError fieldError : list) {
			sb.append(fieldError.getDefaultMessage()).append(System.getProperty("line.separator"));
			res.setMsg(sb.toString());
		}
		return res;
	}
	
	
	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	@ResponseBody
	public ResMsg exceptionHandler(UnsatisfiedServletRequestParameterException ex) {
		log.error("没有匹配到请求处理器",ex);
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setMsg("没有匹配到请求处理器");
		res.setExceptionType(ex.getClass().getName());
		return res;
	}
	
	@ExceptionHandler(RpcException.class)
	@ResponseBody
	public ResMsg exceptionHandler(RpcException ex) {
		log.error("调用远程服务异常",ex);
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setMsg("调用远程服务异常");
		res.setExceptionType(ex.getClass().getName());
		return res;
	}
	
	//未知的异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResMsg exceptionHandler(Exception ex) {
		log.error("其他异常",ex);
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setMsg("其他异常");
		res.setExceptionType(ex.getClass().getName());
		return res;
	}
	
	//统一处理Controller层的异常
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ResMsg exceptionHandler(BusinessException ex) {
		ResMsg res = new ResMsg();
		res.setSuccess(false);
		res.setCode(ex.getCode());
		res.setMsg(ex.getMsg());
		return res;
	}
	
}
