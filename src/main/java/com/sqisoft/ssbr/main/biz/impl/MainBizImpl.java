package com.sqisoft.ssbr.main.biz.impl;

import com.sqisoft.ssbr.main.biz.MainBiz;
import com.sqisoft.ssbr.main.dao.MainDAO;

public class MainBizImpl implements MainBiz {
	private MainDAO mainDAO;

	public void setMainDAO(MainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}

	@Override
	public String getMysqlServerTime() {
		return mainDAO.getMysqlServerTime();
	}
	
	
}
