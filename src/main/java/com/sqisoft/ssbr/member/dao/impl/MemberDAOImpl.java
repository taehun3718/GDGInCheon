package com.sqisoft.ssbr.member.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqisoft.ssbr.member.dao.MemberDAO;
import com.sqisoft.ssbr.member.vo.MemberVO;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

	private static Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	@Override
	public MemberVO doLogin(MemberVO memberVO) {
		// logger.info("ID, PWD" + memberVO.getId() + "/" + memberVO.getPwd());
		MemberVO vo = getSqlSession().selectOne("memberDAO.login", memberVO);
		// logger.info("VO: " + vo.getId() +"/" + vo.getIsDeleteYN());
		if (vo == null)
			return null;
		else
			return vo;
	}

}
