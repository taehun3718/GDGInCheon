/*******************************************************
 * 
 * 2016.03.31 SQISOFT TaeHoon Kim
 * Copyright(c) All Right Reserved
 * 
 * Description	:	ssbridge Ȩ�������� �����ֱ� ���� MainController
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
		ModelAndView view = ModelAndViewUtil.getMessageView("�׽�Ʈ", "DESC: alert");
		return view;
	}
	
	/**������ ������
	@RequestMapping("/showSchedule")
	public ModelAndView showScheduleList() {
		
		logger.info("show schedule list LOGGGING");
		return new ModelAndView("main/showSchedule");
	}**/
	
	/**
	 * ��� ���� Ȯ���� ���Ͽ� ���� �������̴�. ����� ����ϰ� ���� �ʴ�.
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
	 * ���� ����� ���� �������� ȣ���Ѵ�.
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
	 * ���Ȼ���� ssbridge ��ǰ �Ұ� �������� ȣ���Ѵ�.
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
	 * �������� ���� ����Ʈ�� ȣ���Ų��.
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
	 * ���Ȼ���� ���Ϲ��� ��ǰ �Ұ� �������� ȣ���Ѵ�.
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
