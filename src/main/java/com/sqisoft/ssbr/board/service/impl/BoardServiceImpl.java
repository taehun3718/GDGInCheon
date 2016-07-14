package com.sqisoft.ssbr.board.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sqisoft.ssbr.board.biz.BoardBiz;
import com.sqisoft.ssbr.board.service.BoardService;
import com.sqisoft.ssbr.member.vo.EngineerVO;
import com.sqisoft.ssbr.util.AjaxUtil;

public class BoardServiceImpl implements BoardService{

	private BoardBiz boardBiz;
	
	public void setBoardBiz(BoardBiz boardBiz) {
		this.boardBiz = boardBiz;
	}

	@Override
	public List<EngineerVO> getEngineerList() {
		return boardBiz.getEngineerList();
	}

	@Override
	public void checkDuplicateUserID(String eng_id, HttpServletResponse response) {
		
		boolean isDuplicateUserID = this.boardBiz.checkDuplicateUserID(eng_id); 
		AjaxUtil.sendResponse(response, isDuplicateUserID+"");
	}

}
