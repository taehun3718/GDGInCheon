package com.sqisoft.ssbr.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqisoft.ssbr.member.vo.MemberVO;
import com.sqisoft.ssbr.util.LoginAttr;
import com.sqisoft.ssbr.util.SessionStore;

public class SessionListener implements HttpSessionListener{
	private static Logger logger = LoggerFactory.getLogger(SessionListener.class);	

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		logger.info("SESSION CREATED / ID:" + event.getSession().getId());
		logger.info("SESS TIME:" + event.getSession().getMaxInactiveInterval());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		logger.info("SESSION: " + event.getSession().getAttribute(LoginAttr.MEMBER_ATTR));
		SessionStore sessionStore = SessionStore.getInstance();
		MemberVO vo = (MemberVO) event.getSession().getAttribute(LoginAttr.MEMBER_ATTR);
		if(vo!=null){
			sessionStore.removeSession(vo.getId());
			logger.info("SESSION DESTROYED");
		}
		else
			logger.info("SESSION IS NOT DESTROYED");
		
		
	}

}
