package com.recover.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.recover.exception.BusinessException;

import net.minidev.json.JSONObject;

public class RestInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(RestInterceptor.class);
	private static final String tokenHeader = "Authorization";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(204);
			return true;
		}
		log.info("调用接口:method="+request.getParameter("method")+",params="+JSON.toJSONString(request.getParameterMap()));
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		RequestAuth requestAuth = handlerMethod.getMethodAnnotation(RequestAuth.class);
		if(requestAuth!=null && requestAuth.auth()==false){//非验证API
			return true;//跳过验证
		}
		//验证
		String token = request.getHeader(tokenHeader);
		if(token == null || token.equals("")) {
			throw new BusinessException(ResMsg.CODE_TOKEN_NOTFOUND, "没有找到token");
		} else {
			JWSObject jwsObject = JWSObject.parse(token);
			Payload payload = jwsObject.getPayload();
			JSONObject obj = payload.toJSONObject();
			String roleNames = String.valueOf(obj.get("roleNames"));
			Authentication auth = new Authentication();
			if(obj.containsKey("platformId")) {
				Long platformId = Long.valueOf(String.valueOf(obj.get("platformId")));
				auth.setPlatformId(platformId);
			}
			if(obj.containsKey("userid")) {
				Long userId = Long.valueOf(String.valueOf(obj.get("userid")));
				auth.setUserId(userId);
			}
			if(obj.containsKey("orgId")) {
				Long orgId = Long.valueOf(String.valueOf(obj.get("orgId")));
				auth.setOrgId(orgId);
			}
			if(obj.containsKey("orgid")) {//为兼容老版本的接口
				String orgid = String.valueOf(obj.get("orgid"));
				orgid = orgid.replace(",", "");
				Long orgId = Long.valueOf(orgid);
				auth.setOrgId(orgId);
			}
			auth.setRoleNames(roleNames);
			SecurityContext.setContext(auth);
			JwtUtil.verify(jwsObject);
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//log.info("处理中");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null) {
			log.error("接口处理异常:"+ex);
			throw ex;
		}
	}

}
