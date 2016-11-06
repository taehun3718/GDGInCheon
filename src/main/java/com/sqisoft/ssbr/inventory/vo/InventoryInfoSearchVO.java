package com.sqisoft.ssbr.inventory.vo;

import com.sqisoft.ssbr.util.Paging;

public class InventoryInfoSearchVO {
	private Paging paging;
	private String searchType;	//0. 순번/ 1. 이름 / 2. 직급 / 3.아이디
	private String searchKeyword;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
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
}
