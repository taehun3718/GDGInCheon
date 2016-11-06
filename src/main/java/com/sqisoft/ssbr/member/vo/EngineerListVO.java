package com.sqisoft.ssbr.member.vo;

import java.util.ArrayList;
import java.util.List;

import com.sqisoft.ssbr.util.Paging;

public class EngineerListVO {
	private List<EngineerVO> engineerList;
	private Paging paging;
	public List<EngineerVO> getEngineerList() {
		return engineerList;
	}
	public void setEngineerList(List<EngineerVO> engineerList) {
		this.engineerList = engineerList;
	}
	
	public List<EngineerVO> getArticleList() {
		List<EngineerVO> temp = new ArrayList<EngineerVO>();
		temp.addAll(engineerList);
		return temp;
	}
	public void setSearchVOList(List<EngineerVO> articleList) {
		List<EngineerVO> temp = new ArrayList<EngineerVO>();
		temp.addAll(articleList);
		this.engineerList = temp;
	}
	
	public Paging getPaging() {
		return paging.getClone();
	}
	public void setPaging(Paging paging) {
		this.paging = paging.getClone();
	}
}
