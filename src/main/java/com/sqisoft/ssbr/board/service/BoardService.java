package com.sqisoft.ssbr.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sqisoft.ssbr.member.vo.EngineerVO;

public interface BoardService {
	public List<EngineerVO> getEngineerList();
	/***
	 * �ߺ��� id�� �ִ��� Ajax����� ���Ͽ� Ȯ���Ѵ�.
	 * @param eng_id
	 * @param response
	 */
	public void checkDuplicateUserID(String eng_id, HttpServletResponse response);
}
