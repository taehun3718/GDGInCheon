package com.sqisoft.ssbr.member.vo;

import com.sqisoft.ssbr.util.Paging;

public class EngineerSearchVO {
	private Paging paging;
	private String searchType;	//0. 순번/ 1. 이름 / 2. 직급 / 3.아이디
	private String searchKeyword;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public Paging getPaging() {
		return paging.getClone();
	}

	public void setPaging(Paging paging) {
		this.paging = paging.getClone();
	}
}
