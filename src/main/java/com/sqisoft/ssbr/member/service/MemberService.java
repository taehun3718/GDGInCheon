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
	 * 엔지니어 리스트를 불러온다.
	 * @return
	 */
	public ModelAndView getEngineerList(HttpServletRequest request);
	/***
	 * 중복된 엔지니어 리스트가 있는지 확인한다.
	 * @param eng_id
	 * @param response
	 */
	public void checkDuplicateUserID(String eng_id, HttpServletResponse response);

}
