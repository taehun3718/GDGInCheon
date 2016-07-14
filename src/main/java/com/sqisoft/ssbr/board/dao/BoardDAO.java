package com.sqisoft.ssbr.board.dao;

import java.util.List;
import com.sqisoft.ssbr.member.vo.EngineerVO;

public interface BoardDAO {
	public List<EngineerVO> getEngineerList();

	public boolean checkDuplicateUserID(String eng_id);
}
