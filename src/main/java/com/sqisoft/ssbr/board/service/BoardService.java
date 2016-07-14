package com.sqisoft.ssbr.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sqisoft.ssbr.member.vo.EngineerVO;

public interface BoardService {
	public List<EngineerVO> getEngineerList();
	/***
	 * 중복된 id가 있는지 Ajax통신을 통하여 확인한다.
	 * @param eng_id
	 * @param response
	 */
	public void checkDuplicateUserID(String eng_id, HttpServletResponse response);
}
