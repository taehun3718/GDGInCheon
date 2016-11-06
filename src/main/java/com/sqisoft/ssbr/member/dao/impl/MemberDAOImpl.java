package com.sqisoft.ssbr.member.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqisoft.ssbr.member.dao.MemberDAO;
import com.sqisoft.ssbr.member.vo.EngineerSearchVO;
import com.sqisoft.ssbr.member.vo.EngineerVO;
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

	@Override
	public List<EngineerVO> getEngineerList() {
		return getSqlSession().selectList("memberDAO.engineerList");
	}

	@Override
	public boolean checkDuplicateUserID(String eng_id) {
		int count = getSqlSession().selectOne("memberDAO.checkDuplicateUserID", eng_id );
		return count == 1;
		
	}

	@Override
	public List<EngineerVO> getEngineerList(EngineerSearchVO engineerSearchVO) {
		logger.info("[EngineerSearchINFOMSG]" + engineerSearchVO.getSearchKeyword() 
				+ "/" 
				+ engineerSearchVO.getSearchType() + "/"
				+ engineerSearchVO.getPaging() + "/"
				+ engineerSearchVO.getPaging().getStartArticleNumber() + "<=SSS" + engineerSearchVO.getPaging().getEndArticleNumber());
		List<EngineerVO> tmp = getSqlSession().selectList("memberDAO.engineerListPage", engineerSearchVO);
		
		logger.info("GET SIZE:" + tmp.size());
		
		return getSqlSession().selectList("memberDAO.engineerListPage", engineerSearchVO);
	}
	
	@Override
	public int getEngineerCount(EngineerSearchVO engineerSearchVO) {
		return getSqlSession().selectOne("memberDAO.engineerCount", engineerSearchVO);
	}

}
