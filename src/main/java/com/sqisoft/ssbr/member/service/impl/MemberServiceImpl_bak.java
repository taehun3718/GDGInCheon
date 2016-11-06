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
	 * �α����� �����ϴ� �޼ҵ��̴�. DB���� �о� �鿩�� ������ true ������ false ������ �Ѵ�.
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
			/** �α��ν� ����ڿ��� �α��� ���θ� ���� �α� �ƿ��� �ϰ� �Ϸ��� ���, ���� �������� �ʰ� ����. **/
			/*
			 * else { int i = JOptionPane .showConfirmDialog(null ,
			 * "�̹� �α��� �Ǿ� �ִ� ���̵��Դϴ�. �α� �ƿ��� �Ͻðڽ��ϱ�?" , "�α��� Ȯ��" ,
			 * JOptionPane.YES_NO_OPTION); //������ ������ ���� �ٸ� ������ ������ �ֱ� ������,
			 * Invalidate �� removeSession ó���� �ؾ� �ϸ� //���� �������� ��� �α����� �� ���� ���,
			 * �α��� �������� ���� �ʰ� �ٸ� �������� ������ ó���ؾ� ��. if(i==0) {
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
		 * setSearchVOInSession �� ������ ������ ���� ��� �˻� ���� Attribute�� �˻�� �߰��Ѵ�. �˻���
		 * ������ 2������ ����ȴ�. 1. �� �������� ���� ����Ǿ��� �� _SEARCH_KEYWORD ���� ������Ʈ �߰� 2.
		 * �˻����� �� 2.1 ����¡�� 0�������̰� �˻�� ����� ��� _SEARCH_KEYWORD_ ���� ������Ʈ ���� �� �߰�
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
		// TODO : �Է¹��� ���� ����ְ�, �˻� ����¡�� 1�� �� ó��
		//
		
		try {
			logger.info("LOGINFO CURRENT K=" + searchVOInSession.getSearchKeyword() + "//compare//"
					+ URLDecoder.decode(RequestUtil.getParam(request, "keyword", ""), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		*//**
		 * setPaging �����Ͼ� ����Ʈ�� ���� ����¡�� �����ϰ� �� �������� ���ǿ� �˻� ������ �����Ѵ�. - request:
		 * �˻��ϴ� ��� (POST ���)�� �����Ͱ� �ִ��� Ȯ���ϱ� ���� ��ü - engineerSearchVO : DB �˻� ����
		 * ValueObject - session : ���� ���������� ���ǿ� �˻� �����͸� �ֱ� ���� ��ü -
		 * searchVOInSession : ���� ���������� ���ǿ� ����ִ� �˻� �������� ��ü
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