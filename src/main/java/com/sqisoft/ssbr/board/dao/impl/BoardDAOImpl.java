package com.sqisoft.ssbr.board.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sqisoft.ssbr.board.dao.BoardDAO;
import com.sqisoft.ssbr.member.vo.EngineerVO;

public class BoardDAOImpl extends SqlSessionDaoSupport implements BoardDAO {

	@Override
	public List<EngineerVO> getEngineerList() {
		return getSqlSession().selectList("boardDAO.engineerList");
	}

	@Override
	public boolean checkDuplicateUserID(String eng_id) {
		int count = getSqlSession().selectOne("boardDAO.checkDuplicateUserID",eng_id );
		return count == 1;
	}

}
