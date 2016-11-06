package com.sqisoft.ssbr.member.dao;

import java.util.List;

import com.sqisoft.ssbr.member.vo.EngineerSearchVO;
import com.sqisoft.ssbr.member.vo.EngineerVO;
import com.sqisoft.ssbr.member.vo.MemberVO;

public interface MemberDAO {

	public MemberVO doLogin(MemberVO memberVO);

	public List<EngineerVO> getEngineerList(EngineerSearchVO engineerSearchVO);

	public boolean checkDuplicateUserID(String eng_id);

	public List<EngineerVO> getEngineerList();

	int getEngineerCount(EngineerSearchVO engineerSearchVO);

}
