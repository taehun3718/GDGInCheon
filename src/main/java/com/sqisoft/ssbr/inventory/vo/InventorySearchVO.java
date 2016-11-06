package com.sqisoft.ssbr.inventory.vo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqisoft.ssbr.inventory.dao.InventoryDAO;
import com.sqisoft.ssbr.util.Attribute;
import com.sqisoft.ssbr.util.Paging;
import com.sqisoft.ssbr.util.RequestUtil;
import com.sqisoft.ssbr.util.SearchVO;

public class InventorySearchVO implements SearchVO{
	
	private InventoryInfoSearchVO inventoryInfoSearchVO;	//
	private InventoryInfoSearchVO searchVOInSession; 		//현재 세션에 검색 정보가 있을 때
	private InventoryListVO list;
	
	private HttpSession session;
	
	private static Logger logger = LoggerFactory.getLogger(InventorySearchVO.class);
	
	public InventorySearchVO(HttpServletRequest request
			 				, Object dao) {
		initSearchVO(request);
		setPaging(request, (InventoryDAO)dao);
	}
	
	@Override
	public void initSearchVO(HttpServletRequest request) {
		this.session = request.getSession();
		
		inventoryInfoSearchVO = new InventoryInfoSearchVO();
		searchVOInSession = (InventoryInfoSearchVO) session.getAttribute(Attribute.SEARCH_KEYWORD_INV);
		
		if (searchVOInSession == null) {
			searchVOInSession = new InventoryInfoSearchVO();
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
				session.removeAttribute(Attribute.SEARCH_KEYWORD_INV);
				searchVOInSession = new InventoryInfoSearchVO();
				searchVOInSession.setPaging(new Paging());
			}
		}
	}

	@Override
	public void setPaging(HttpServletRequest request, Object inventoryDao) {
		Paging paging = new Paging();
		paging.setPageNumber(RequestUtil.getParam(request, "pageNo", searchVOInSession.getPaging().getPageNo() + ""));
		
		inventoryInfoSearchVO.setPaging(paging);
		inventoryInfoSearchVO
				.setSearchKeyword(RequestUtil.getParam(request, "keyword", searchVOInSession.getSearchKeyword()));
		inventoryInfoSearchVO.setSearchType(RequestUtil.getParam(request, "type", searchVOInSession.getSearchType()));

		session.setAttribute(Attribute.SEARCH_KEYWORD_INV, inventoryInfoSearchVO);
		
		// set Paging Logic/////////////////////////////////////////////////////////////
		int cnt = ((InventoryDAO) inventoryDao).getInventoryCount(inventoryInfoSearchVO);
		logger.info("getinventoryCount CNT:" + cnt);
		Paging pagingInventoryList = inventoryInfoSearchVO.getPaging();
		pagingInventoryList.setTotalArticleCount(cnt);

		list = new InventoryListVO();
		list.setPaging(pagingInventoryList);
		logger.info("pagingInventoryList: " + list.getPaging().getTotalCount());
		if (cnt > 0) {
			list.setSearchVOList(((InventoryDAO) inventoryDao).getInventoryList(inventoryInfoSearchVO));
		} else {
			list.setSearchVOList(new ArrayList<InventoryInfoVO>());
		}
		//////////////////////////////////////////////////////////////////////////////
		for (InventoryInfoVO vo : list.getInventoryList())
			logger.info(vo.getInventSeqno() 
					+ "/" + vo.getInventName() 
					+ "/" + vo.getInventCategory() 
					+ "/" + vo.getInventUpdt());

		logger.info("Paging settings:" + paging.getStartArticleNumber() + "/" + paging.getEndArticleNumber());
		//////////////////////////////////////////////////////////////////////////////
	}
	
	public InventoryInfoSearchVO getInventoryInfoSearchVO() {
		return inventoryInfoSearchVO;
	}

	public InventoryListVO getList() {
		return list;
	}

}
