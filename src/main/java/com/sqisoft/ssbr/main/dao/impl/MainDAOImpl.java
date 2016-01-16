package com.sqisoft.ssbr.main.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sqisoft.ssbr.main.dao.MainDAO;

public class MainDAOImpl extends SqlSessionDaoSupport implements MainDAO {

	@Override
	public String getMysqlServerTime() {
		return getSqlSession().selectOne("mainDAO.sample");
	}
	
}
