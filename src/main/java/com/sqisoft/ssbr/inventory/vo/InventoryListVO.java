package com.sqisoft.ssbr.inventory.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqisoft.ssbr.member.service.impl.MemberServiceImpl;
import com.sqisoft.ssbr.util.Paging;

public class InventoryListVO {
	private List<InventoryInfoVO> inventoryList;
	private Paging paging;
	
	private static Logger logger = LoggerFactory.getLogger(InventoryListVO.class);
	
	public List<InventoryInfoVO> getInventoryList() {
		return inventoryList;
	}
	
	public void setInventoryList(List<InventoryInfoVO> inventoryList) {
		this.inventoryList = inventoryList;
	}
	
	public List<InventoryInfoVO> setInventoryList() {
		List<InventoryInfoVO> temp = new ArrayList<InventoryInfoVO>();
		temp.addAll(inventoryList);
		return temp;
	}
	public void setSearchVOList(List<InventoryInfoVO> articleList) {
		List<InventoryInfoVO> temp = new ArrayList<InventoryInfoVO>();
		temp.addAll(articleList);
		this.inventoryList = temp;
	}
	
	public Paging getPaging() {
		return paging.getClone();
	}
	public void setPaging(Paging paging) {
		this.paging = paging.getClone();
	}
}
