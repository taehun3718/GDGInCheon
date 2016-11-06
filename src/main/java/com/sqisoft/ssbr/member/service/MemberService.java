package com.sqisoft.ssbr.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.member.vo.EngineerVO;
import com.sqisoft.ssbr.member.vo.MemberVO;

public interface MemberService {

	public boolean doLogin(MemberVO memberVO,  HttpServletRequest request, HttpServletResponse response);
	public void doLogout(HttpServletRequest request);
	
	/**
	 * �����Ͼ� ����Ʈ�� �ҷ��´�.
	 * @return
	 */
	public ModelAndView getEngineerList(HttpServletRequest request);
	/***
	 * �ߺ��� �����Ͼ� ����Ʈ�� �ִ��� Ȯ���Ѵ�.
	 * @param eng_id
	 * @param response
	 */
	public void checkDuplicateUserID(String eng_id, HttpServletResponse response);

}
