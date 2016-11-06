package com.sqisoft.ssbr.util;

import javax.servlet.http.HttpServletRequest;

public interface SearchVO {
	/**
	 * SearchVO를 초기화 구현 필요
	 * @param request
	 */
	public void initSearchVO(HttpServletRequest request);
	/**
	 * SearchVO를 페이징 해줄 초기화 구현 필요
	 * @param request, Object
	 */
	public void setPaging(HttpServletRequest request, Object dao);
}
