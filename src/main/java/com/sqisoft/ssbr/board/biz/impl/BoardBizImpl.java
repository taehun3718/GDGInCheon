package com.sqisoft.ssbr.board.biz.impl;

import java.util.List;

import com.sqisoft.ssbr.board.biz.BoardBiz;
import com.sqisoft.ssbr.board.dao.BoardDAO;
import com.sqisoft.ssbr.member.vo.EngineerVO;

public class BoardBizImpl implements BoardBiz{
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	public List<EngineerVO> getEngineerList() {
		return boardDAO.getEngineerList();
	}

	@Override
	public boolean checkDuplicateUserID(String eng_id) {
		return boardDAO.checkDuplicateUserID(eng_id);
	}

}
