package com.sqisoft.ssbr.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sqisoft.ssbr.member.vo.MemberVO;

public interface MemberService {

	public boolean doLogin(MemberVO memberVO,  HttpServletRequest request, HttpServletResponse response);
	public void doLogout(HttpServletRequest request);

}
