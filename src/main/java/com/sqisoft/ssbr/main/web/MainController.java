/*******************************************************
 * 
 * 2016.03.31 SQISOFT TaeHoon Kim
 * Copyright(c) All Right Reserved
 * 
 * Description	:	ssbridge 홈페이지를 보여주기 위한 MainController
 * Doc author	:	TaeHoon Kim
 * 
 *******************************************************/
package com.sqisoft.ssbr.main.web;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.main.service.MainService;
import com.sqisoft.ssbr.member.service.MemberService;
import com.sqisoft.ssbr.util.DataParsingUtil;
import com.sqisoft.ssbr.util.Attribute;
import com.sqisoft.ssbr.util.ModelAndViewUtil;

@Controller
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);	
	private MainService mainService;
	private MemberService memberService;
	
	public MemberService getMemberService() {
		return memberService;
	}


	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}


	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	
	@RequestMapping("/loginError")
	public ModelAndView mainView() {
		
		//logger.info("INDEX LOGGGING");
		//logger.info("Get Server Time:" +  mainService.getMysqlServerTime());
		//return this.mainService.mainView();
		ModelAndView view = ModelAndViewUtil.getMessageView("테스트", "DESC: alert");
		return view;
	}
	
	/**디버깅용 페이지
	@RequestMapping("/showSchedule")
	public ModelAndView showScheduleList() {
		
		logger.info("show schedule list LOGGGING");
		return new ModelAndView("main/showSchedule");
	}**/
	
	/**
	 * 백신 버전 확인을 위하여 만든 페이지이다. 현재는 사용하고 있지 않다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/vMain")
	public ModelAndView virusDownloadMain(HttpServletRequest request) {
		
		logger.info("User attempts site of virusDownloadMain web page");
		ModelAndView view = new ModelAndView();
		view.addObject("vo", new DataParsingUtil().getSophosInfoVO());
		view.addObject("MemberVO", request.getSession().getAttribute(Attribute.MEMBER_ATTR));
		view.setViewName("main/virusFileCheck");
		return view;
	}
	
	/**
	 * 보안 사업팀 메인 페이지를 호출한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssbizMain")
	public ModelAndView ssBizMain(HttpServletRequest request) {
		
		logger.info("User attempts site of ssbizMain web page");
		ModelAndView view = new ModelAndView();
		view.setViewName("main/ssbizMain");
		view.addObject("MemberVO"
				, request.getSession().getAttribute(Attribute.MEMBER_ATTR));

		return view;
	}
	
	/**
	 * 보안사업팀 ssbridge 제품 소개 페이지를 호출한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/productSSBR")
	public ModelAndView ssBizProductSSBR(HttpServletRequest request) {
		
		logger.info("User attempts site of ssbizMain web page");
		ModelAndView view = new ModelAndView();
		view.setViewName("main/productSSBR");
		view.addObject("MemberVO"
				, request.getSession().getAttribute(Attribute.MEMBER_ATTR));
		return view;
	}
	
	/**
	 * 유지보수 관리 사이트를 호출시킨다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssBizMaintence")
	public ModelAndView ssBizMaintence(HttpServletRequest request) {
		
		logger.info("User attempts site of maintenance web page");
		ModelAndView view = new ModelAndView();
		view.setViewName("main/maintenance");
		view.addObject("MemberVO"
				, request.getSession().getAttribute(Attribute.MEMBER_ATTR));
		return view;
	}
	
	/**
	 * 보안사업팀 파일무버 제품 소개 페이지를 호출한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssBizProductFMC")
	public ModelAndView ssBizProductFMC(HttpServletRequest request) {
		
		logger.info("User attempts site of ssbizMain web page");
		ModelAndView view = new ModelAndView();
		view.setViewName("main/product");
		return view;
	}
	
}
