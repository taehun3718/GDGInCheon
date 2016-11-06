package com.sqisoft.ssbr.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.member.service.MemberService;
import com.sqisoft.ssbr.member.vo.EngineerSearchVO;
import com.sqisoft.ssbr.member.vo.EngineerVO;
import com.sqisoft.ssbr.util.Attribute;
import com.sqisoft.ssbr.util.Paging;
import com.sqisoft.ssbr.util.RequestUtil;

@Controller
public class MemberController {
	
	private MemberService memberService;

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	/**
	 * 엔지니어 관리 사이트를 호출시킨다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssBizEngineer")
	public ModelAndView ssBizEngineer(HttpServletRequest request) {
		
		logger.info("User attempts site of ssBizEngineer web page");
		ModelAndView view =  memberService.getEngineerList(request);
		
//		for(EngineerVO vo : engineerList)
//			logger.info(vo.getEng_seqNo() + "/" + vo.getEng_id() + "/" + vo.getEng_nm() + "/"  + vo.getEng_rank());
//		logger.info("SIZE:" + engineerList.size());
//		
//		
//		ModelAndView view = new ModelAndView();
		view.addObject("MemberVO"
				, request.getSession().getAttribute(Attribute.MEMBER_ATTR));
//		view.addObject("EngineerVO"
//				, engineerList);
		view.setViewName("main/engineer");
		return view;
		
	}
	
	@RequestMapping("/ssBizEngineer/doRegistEngineer")
	public ModelAndView doRegistEngineer(@Valid EngineerVO engineerVO
										, HttpServletRequest request) {
		logger.info("TMCI000:" + engineerVO.getEng_nm() 
		+ "/" + engineerVO.getEng_id() 
		+ "/" + engineerVO.getEng_rank() 
		+ "/" + engineerVO.getEng_pswd());
		
		return new ModelAndView("redirect:/ssBizEngineer");
	}
	
	@RequestMapping("/ssBizEngineer/checkDuplicateUserIDAjax")
	public void checkDuplicateUserID(
			@RequestParam String eng_id
			, HttpServletResponse response){
		
		this.memberService.checkDuplicateUserID(eng_id, response); 
	}
}
