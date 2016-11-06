/**
 * 
 */
/**
 * @author TaeHoonKim
 *
 */
package com.sqisoft.ssbr.member.vo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqisoft.ssbr.member.dao.MemberDAO;
import com.sqisoft.ssbr.util.Attribute;
import com.sqisoft.ssbr.util.Paging;
import com.sqisoft.ssbr.util.RequestUtil;
import com.sqisoft.ssbr.util.SearchVO;

public class MemberSearchVO implements SearchVO{

	private EngineerSearchVO engineerSearchVO;
	private EngineerSearchVO searchVOInSession;
	private EngineerListVO list;
	
	private HttpSession session;
	
	private static Logger logger = LoggerFactory.getLogger(MemberSearchVO.class);
	
	public MemberSearchVO(HttpServletRequest request
						 , Object dao) {
		initSearchVO(request);
		setPaging(request, (MemberDAO)dao);
	}
	
	@Override
	public void initSearchVO(HttpServletRequest request) {
		
		this.session = request.getSession();
		
		engineerSearchVO = new EngineerSearchVO();
		searchVOInSession = (EngineerSearchVO) session.getAttribute(Attribute.SEARCH_KEYWORD);
		
		if (searchVOInSession == null) {
			searchVOInSession = new EngineerSearchVO();
			searchVOInSession.setPaging(new Paging());
			logger.info("TMSI000 set SearchVOInSession"); 
		} 
		else if(true) {
			if(searchVOInSession.getSearchKeyword().equals("")!=true &&
				RequestUtil.getParam(request, "keyword", "").equals("")	) {
				// TMSI001 Session Keyword is not blank and keyword is blank
				logger.info("TMSI001 Session Keyword is not blank and keyword is blank =>" 
						+ searchVOInSession.getSearchKeyword()  + "/" + RequestUtil.getParam(request, "keyword", ""));
			}
			if(RequestUtil.getParam(request, "pageNo", "-1").equals("-1")){
				
				// TMSI002 Session paging is null
				logger.info("TMSI002 Session paging is null");
				logger.info("TMSI003 set SearchVOInSession Update");
				// TMSI003 setSearchVOInSession Update
				session.removeAttribute(Attribute.SEARCH_KEYWORD);
				searchVOInSession = new EngineerSearchVO();
				searchVOInSession.setPaging(new Paging());
			}
		}
	}

	@Override
	public void setPaging(HttpServletRequest request,
							Object memberDAO) {
		Paging paging = new Paging();
		paging.setPageNumber(RequestUtil.getParam(request, "pageNo", searchVOInSession.getPaging().getPageNo() + ""));
		engineerSearchVO.setPaging(paging);
		engineerSearchVO
				.setSearchKeyword(RequestUtil.getParam(request, "keyword", searchVOInSession.getSearchKeyword()));
		engineerSearchVO.setSearchType(RequestUtil.getParam(request, "type", searchVOInSession.getSearchType()));

		//logger.info("DATA:" + RequestUtil.getParam(request, "keyword", searchVOInSession.getSearchKeyword()) + "///"
		//		+ RequestUtil.getParam(request, "type", searchVOInSession.getSearchType()));
		session.setAttribute(Attribute.SEARCH_KEYWORD, engineerSearchVO);
		
		// set Paging Logic/////////////////////////////////////////////////////////////
		//logger.info(
		//		"engineerSearchVO :" + engineerSearchVO.getSearchKeyword() + "/" + engineerSearchVO.getSearchType());
		int cnt = ((MemberDAO) memberDAO).getEngineerCount(engineerSearchVO);
		logger.info("getEngineerCount CNT:" + cnt);
		Paging pagingEngineerList = engineerSearchVO.getPaging();
		pagingEngineerList.setTotalArticleCount(cnt);

		list = new EngineerListVO();
		list.setPaging(pagingEngineerList);
		logger.info("pagingEngineerList: " + list.getPaging().getTotalCount());
		if (cnt > 0) {
			list.setSearchVOList(((MemberDAO) memberDAO).getEngineerList(engineerSearchVO));
		} else {
			list.setSearchVOList(new ArrayList<EngineerVO>());
		}
		//////////////////////////////////////////////////////////////////////////////
		for (EngineerVO vo : list.getArticleList())
			logger.info(vo.getEng_seqNo() + "/" + vo.getEng_id() + "/" + vo.getEng_nm() + "/" + vo.getEng_rank());

		logger.info("Paging settings:" + paging.getStartArticleNumber() + "/" + paging.getEndArticleNumber());
		//////////////////////////////////////////////////////////////////////////////
	}

	public EngineerListVO getList() {
		return list;
	}

	public EngineerSearchVO getEngineerSearchVO() {
		return engineerSearchVO;
	}

	public EngineerSearchVO getSearchVOInSession() {
		return searchVOInSession;
	}
	
}