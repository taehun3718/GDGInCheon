package com.sqisoft.ssbr.member.service.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.member.dao.MemberDAO;
import com.sqisoft.ssbr.member.service.MemberService;
import com.sqisoft.ssbr.member.vo.MemberSearchVO;
import com.sqisoft.ssbr.member.vo.MemberVO;
import com.sqisoft.ssbr.util.AjaxUtil;
import com.sqisoft.ssbr.util.Attribute;
import com.sqisoft.ssbr.util.SearchVO;
import com.sqisoft.ssbr.util.SearchVoFactory;
import com.sqisoft.ssbr.util.SessionStore;

public class MemberServiceImpl_bak implements MemberService {

	private MemberDAO memberDAO;
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl_bak.class);

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	/**
	 * 로그인을 수행하는 메소드이다. DB에서 읽어 들여서 있으면 true 없으면 false 리턴을 한다.
	 */
	@Override
	public boolean doLogin(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// DO login (DB)
		MemberVO vo = memberDAO.doLogin(memberVO);

		boolean logined = vo == null ? false : true;
		if (logined) {
			SessionStore sessionStore = SessionStore.getInstance();

			if (!sessionStore.isExists(memberVO.getId())) {
				session.setAttribute(Attribute.MEMBER_ATTR, vo);
				sessionStore.putSession(memberVO.getId(), session);
				return true;
			} else {
				HttpSession sess = sessionStore.getSession(memberVO.getId());
				if (sess == null) {
					sessionStore.removeSession(memberVO.getId());
				} else {
					sessionStore.getSession(memberVO.getId()).invalidate();
				}
				sessionStore.removeSession(memberVO.getId());
				session.setAttribute(Attribute.MEMBER_ATTR, vo);
				// ...
				sessionStore.putSession(memberVO.getId(), session);

				return true;
			}
			/** 로그인시 사용자에게 로그인 여부를 묻고 로그 아웃을 하게 하려는 기능, 현재 구현하지 않고 있음. **/
			/*
			 * else { int i = JOptionPane .showConfirmDialog(null ,
			 * "이미 로그인 되어 있는 아이디입니다. 로그 아웃을 하시겠습니까?" , "로그인 확인" ,
			 * JOptionPane.YES_NO_OPTION); //세션은 브라우져 마다 다른 세션을 가지고 있기 때문에,
			 * Invalidate 및 removeSession 처리를 해야 하며 //같은 브라우져일 경우 로그인이 되 있을 경우,
			 * 로그인 페이지로 가지 않고 다른 페이지로 가도록 처리해야 함. if(i==0) {
			 * sessionStore.getSession(memberVO.getId()).invalidate();
			 * sessionStore.removeSession(memberVO.getId());
			 * session.setAttribute(LoginAttr.MEMBER_ATTR, memberVO); //...
			 * sessionStore.putSession(memberVO.getId(), session);
			 * 
			 * return true; } else if(i==1){ return false; } }
			 */
		}
		return false;
	}

	@Override
	public void doLogout(HttpServletRequest request) {
		SessionStore sessionStore = SessionStore.getInstance();
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute(Attribute.MEMBER_ATTR);

		if (memberVO != null) {
			sessionStore.getSession(memberVO.getId()).invalidate();
			sessionStore.removeSession(memberVO.getId());
		}
	}

	/* (non-Javadoc)
	 * @see com.sqisoft.ssbr.member.service.MemberService#getEngineerList(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ModelAndView getEngineerList(HttpServletRequest request) {
		
		SearchVoFactory svf = new SearchVoFactory();
		SearchVO vo = svf.getSearchVO("EnginnerSearchVO", request, memberDAO);
		
		ModelAndView view = new ModelAndView();
		view.addObject("engineerSearchVO", ((MemberSearchVO) vo).getEngineerSearchVO());
		view.addObject("engineerList", ((MemberSearchVO) vo).getList());
		
		return view;
		
		/*EngineerSearchVO engineerSearchVO = new EngineerSearchVO();
		HttpSession session = request.getSession();

		EngineerSearchVO searchVOInSession = (EngineerSearchVO) session.getAttribute(Attribute.SEARCH_KEYWORD);

		*//**
		 * setSearchVOInSession 웹 브라우져 세션이 있을 경우 검색 세션 Attribute에 검색어를 추가한다. 검색어
		 * 조건은 2가지로 적용된다. 1. 웹 브라우져가 최초 실행되었을 때 _SEARCH_KEYWORD 세션 오브젝트 추가 2.
		 * 검색했을 때 2.1 페이징이 0페이지이고 검색어가 비었을 경우 _SEARCH_KEYWORD_ 세션 오브젝트 삭제 후 추가
		 * 
		 *//*

		if (searchVOInSession == null) {
			searchVOInSession = new EngineerSearchVO();
			searchVOInSession.setPaging(new Paging());
			logger.info("TMSI000 setSearchVOInSession"); 
					
			
			
		} 
		//testcode
		else if(true) {
			if(searchVOInSession.getSearchKeyword().equals("")!=true &&
				RequestUtil.getParam(request, "keyword", "").equals("")	){
				
				logger.info("TMSI001 Session Keyword is not blank and keyword is blank =>" 
				+ searchVOInSession.getSearchKeyword()  + "/" + RequestUtil.getParam(request, "keyword", ""));
				
			}
			if(RequestUtil.getParam(request, "pageNo", "-1").equals("-1")){
					
				logger.info("TMSI002 Session paging is null");
				session.removeAttribute(Attribute.SEARCH_KEYWORD);
				searchVOInSession = new EngineerSearchVO();
				searchVOInSession.setPaging(new Paging());
				logger.info("TMSI003 setSearchVOInSession UPD");
			}
		}
		else if (
				( searchVOInSession.getSearchKeyword().equals("") 
						|| RequestUtil.getParam(request, "keyword", "").equals(""))
						&& RequestUtil.getParam(request, "page", "0").equals("0")) {
			session.removeAttribute(Attribute.SEARCH_KEYWORD);
			searchVOInSession = new EngineerSearchVO();
			searchVOInSession.setPaging(new Paging());
			System.out.println("setSearchVOInSession UPD");
		}
		// TODO : 입력받은 값이 비어있고, 검색 페이징이 1일 때 처리
		//
		
		try {
			logger.info("LOGINFO CURRENT K=" + searchVOInSession.getSearchKeyword() + "//compare//"
					+ URLDecoder.decode(RequestUtil.getParam(request, "keyword", ""), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		*//**
		 * setPaging 엔지니어 리스트에 대한 페이징을 지정하고 현 브라우져의 세션에 검색 정보를 저장한다. - request:
		 * 검색하는 대상 (POST 방식)의 데이터가 있는지 확인하기 위한 객체 - engineerSearchVO : DB 검색 전용
		 * ValueObject - session : 현재 웹브라우져의 세션에 검색 데이터를 넣기 위한 객체 -
		 * searchVOInSession : 현재 웹브라우져의 세션에 들어있는 검색 데이터의 객체
		 *//*
		Paging paging = new Paging();
		// paging.setPageNumber(request.getParameter("pageNo"));
		paging.setPageNumber(RequestUtil.getParam(request, "pageNo", searchVOInSession.getPaging().getPageNo() + ""));
		engineerSearchVO.setPaging(paging);
		engineerSearchVO
				.setSearchKeyword(RequestUtil.getParam(request, "keyword", searchVOInSession.getSearchKeyword()));
		engineerSearchVO.setSearchType(RequestUtil.getParam(request, "type", searchVOInSession.getSearchType()));

		logger.info("DATA:" + RequestUtil.getParam(request, "keyword", searchVOInSession.getSearchKeyword()) + "///"
				+ RequestUtil.getParam(request, "type", searchVOInSession.getSearchType()));
		session.setAttribute(Attribute.SEARCH_KEYWORD, engineerSearchVO);
		// set Paging
		// logic/////////////////////////////////////////////////////////////
		logger.info(
				"engineerSearchVO :" + engineerSearchVO.getSearchKeyword() + "/" + engineerSearchVO.getSearchType());
		int cnt = memberDAO.getEngineerCount(engineerSearchVO);
		logger.info("getEngineerCount CNT:" + cnt);
		Paging pagingEngineerList = engineerSearchVO.getPaging();
		pagingEngineerList.setTotalArticleCount(cnt);

		EngineerListVO list = new EngineerListVO();
		list.setPaging(pagingEngineerList);
		logger.info("pagingEngineerList: " + list.getPaging().getTotalCount());
		if (cnt > 0) {
			list.setSearchVOList(memberDAO.getEngineerList(engineerSearchVO));
		} else {
			list.setSearchVOList(new ArrayList<EngineerVO>());
		}
		//////////////////////////////////////////////////////////////////////////////
		for (EngineerVO vo : list.getArticleList())
			logger.info(vo.getEng_seqNo() + "/" + vo.getEng_id() + "/" + vo.getEng_nm() + "/" + vo.getEng_rank());

		logger.info("Paging settings:" + paging.getStartArticleNumber() + "/" + paging.getEndArticleNumber());
*/
		
	}

	@Override
	public void checkDuplicateUserID(String eng_id, HttpServletResponse response) {
		boolean isDuplicateUserID = this.memberDAO.checkDuplicateUserID(eng_id);
		AjaxUtil.sendResponse(response, isDuplicateUserID + "");

	}
}