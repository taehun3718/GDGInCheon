package com.sqisoft.ssbr.main.service.impl;

import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.main.biz.MainBiz;
import com.sqisoft.ssbr.main.service.MainService;

public class MainServiceImpl implements MainService{

	private MainBiz mainBiz;
	
	public void setMainBiz(MainBiz mainBiz) {
		this.mainBiz = mainBiz;
	}

	/**DB Ŀ�ؼ� Ȯ�ο� �޼ҵ�**/
	@Override
	public ModelAndView mainView() {
		ModelAndView view = new ModelAndView("main/index");
		view.addObject("TIME", mainBiz.getMysqlServerTime());
		return view;
	}
	
	/**DB Ŀ�ؼ� Ȯ�ο� �޼ҵ�: �ð��� ���´�.**/
	@Override
	public String getMysqlServerTime() {
		return mainBiz.getMysqlServerTime();
	}

}
