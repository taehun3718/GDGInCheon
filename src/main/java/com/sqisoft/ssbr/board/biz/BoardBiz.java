package com.sqisoft.ssbr.board.biz;

import java.util.List;

import com.sqisoft.ssbr.member.vo.EngineerVO;

public interface BoardBiz {
	public List<EngineerVO> getEngineerList();

	public boolean checkDuplicateUserID(String eng_id);
}
