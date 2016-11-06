/*******************************************************
 * 
 * 2016.07.18 SQISOFT TaeHoon Kim
 * Copyright(c) All Right Reserved
 * 
 * Description	:	로그인을 컨트롤
 * Doc author	:	TaeHoon Kim
 * 
 *******************************************************/
package com.sqisoft.ssbr.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sqisoft.ssbr.member.service.MemberService;
import com.sqisoft.ssbr.member.vo.MemberVO;
import com.sqisoft.ssbr.util.Attribute;

@Controller
public class LoginController{

	private MemberService memberService;
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * 로그인을 요구할 경우 호출한다.
	 * @return requiredLogin.jsp
	 */
	@RequestMapping("/requiredLogin")
	public ModelAndView requiredLogin() {
		ModelAndView view  = new ModelAndView();
		view.setViewName("/login/requiredLogin");
		return view;
		
	}
	
	
	/**
	 * 로그인 ID 패스워드를 입력한다. 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request
							, HttpServletResponse response) {
		ModelAndView view  = new ModelAndView();
		if(request.getSession().getAttribute(Attribute.MEMBER_ATTR)==null){
			view.setViewName("/login/login");
		}
		else{
			view.setViewName("redirect:/ssbizMain");
		}
		return view;
	}
	/**
	 * 로그인을 수행한다. 로그인에 실패할 경우, 다시 입력하도록 로그인 화면으로 이동한다.
	 * @param id
	 * @param pwd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doLogin")
	public ModelAndView doLogin(@RequestParam ("id") String id
							, @RequestParam ("pwd") String pwd
							, HttpServletRequest request
							, HttpServletResponse response
							, RedirectAttributes redirectAttr) {
		
		MemberVO memberVO = new MemberVO();

		memberVO.setId(id);
		memberVO.setPwd(pwd);
		
		boolean islogined = memberService.doLogin(memberVO, request, response);

		ModelAndView view  = new ModelAndView();
		if(islogined) {
			redirectAttr.addFlashAttribute("MemberVO", memberVO);
			view.setViewName("redirect:/ssbizMain");	
		}
		else {
			view.addObject("MemberVO", memberVO);
			view.addObject("Error", "loginError");
			view.setViewName("/login/login");	
		}
		return view;
	}
	
	/**
	 * 로그아웃을 수행한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLogout")
	public ModelAndView doLogout(HttpServletRequest request) {
		
		memberService.doLogout(request);
		
		ModelAndView view  = new ModelAndView();
		view.setViewName("redirect:/login");		
		return view;
	}
}
