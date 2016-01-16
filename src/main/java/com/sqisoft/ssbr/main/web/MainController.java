package com.sqisoft.ssbr.main.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.main.service.MainService;
import com.sqisoft.ssbr.util.DataParsingUtil;

@Controller
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);	
	private MainService mainService;
	
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	@RequestMapping("/index")
	public ModelAndView mainView() {
		
		logger.info("INDEX LOGGGING");
		logger.info("Get Server Time:" +  mainService.getMysqlServerTime());
		return this.mainService.mainView();
	}
	
	@RequestMapping("/showSchedule")
	public ModelAndView showScheduleList() {
		
		logger.info("show schedule list LOGGGING");
		return new ModelAndView("main/showSchedule");
	}
	
	@RequestMapping("/vMain")
	public ModelAndView virusDownloadMain() {
		
		logger.info("User attempts site of virusDownloadMain web page");
		ModelAndView view = new ModelAndView();
		view.addObject("vo", new DataParsingUtil().getSophosInfoVO());
		view.setViewName("main/virusFileCheck");
		return view;
	}
	
}
