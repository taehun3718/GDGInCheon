package com.sqisoft.ssbr.main.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqisoft.ssbr.main.dao.MainDAO;

public class MainDAOImpl extends SqlSessionDaoSupport implements MainDAO {
	
	private static Logger logger = LoggerFactory.getLogger(MainDAOImpl.class);	

	@Override
	public String getMysqlServerTime() {
		return getSqlSession().selectOne("mainDAO.sample");
	}
	
}
