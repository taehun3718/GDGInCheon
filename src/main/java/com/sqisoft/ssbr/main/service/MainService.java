package com.sqisoft.ssbr.main.service;

import org.springframework.web.servlet.ModelAndView;

public interface MainService {

	public ModelAndView mainView();
	public String getMysqlServerTime();
}
