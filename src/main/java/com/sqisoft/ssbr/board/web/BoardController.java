package com.sqisoft.ssbr.board.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.sqisoft.ssbr.board.service.BoardService;
@Controller
public class BoardController {
	private BoardService boardService;
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/**
	 * 엔지니어 관리 사이트를 호출시킨다.
	 * @param request
	 * @return
	 *//*
	@RequestMapping("/ssBizEngineer")
	public ModelAndView ssBizEngineer(HttpServletRequest request) {
		
		logger.info("User attempts site of ssBizEngineer web page");

		List<EngineerVO> engineerList = boardService.getEngineerList();
		
		for(EngineerVO vo : engineerList)
			logger.info(vo.getEng_seqNo() + "/" + vo.getEng_id() + "/" + vo.getEng_nm() + "/"  + vo.getEng_rank());
		logger.info("SIZE:" + engineerList.size());
		
		
		ModelAndView view = new ModelAndView();
		view.addObject("MemberVO"
				, request.getSession().getAttribute(LoginAttr.MEMBER_ATTR));
		view.addObject("EngineerVO"
				, engineerList);
		view.setViewName("main/engineer");
		return view;
	}
	
	@RequestMapping("/ssBizEngineer/doRegistEngineer")
	public ModelAndView doRegistEngineer(@Valid EngineerVO engineerVO
										, HttpServletRequest request) {
		logger.info("DATA:" + engineerVO.getEng_nm() 
		+ "/" + engineerVO.getEng_id() 
		+ "/" + engineerVO.getEng_rank() 
		+ "/" + engineerVO.getEng_pswd());
		
		return new ModelAndView("redirect:/ssBizEngineer");
	}
	
	@RequestMapping("/ssBizEngineer/checkDuplicateUserIDAjax")
	public void checkDuplicateUserID(
			@RequestParam String eng_id
			, HttpServletResponse response){
		
		this.boardService.checkDuplicateUserID(eng_id, response); 
	}*/
}
