package com.sqisoft.ssbr.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sqisoft.ssbr.util.Attribute;


public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler) throws Exception {
		
		logger.info("preHandle");
		if(request.getSession().getAttribute(Attribute.MEMBER_ATTR)==null){
			logger.info("get Login/" + request.getSession().getAttribute(Attribute.MEMBER_ATTR));
			response.sendRedirect("/ssbrBiz/requiredLogin");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion");
	}
}
