package com.sqisoft.ssbr.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;

public class InventoryCategoryHashMap {
		
	private Map<Integer, InventoryCategoryVO> map;
	public InventoryCategoryHashMap(List<InventoryCategoryVO> list) {
		/***********************************************************
		 * 디비에서는 sequence별 카테고리명이 지정되어 있는데 이를 HashMap으로 넣는다.
		 * seq		category	code
		 * 1		CD-ROM		CD-R-20161023
		 ************************************************************/
		map = new HashMap<Integer, InventoryCategoryVO>();
				
		// <K:1, V:CD-ROM>
		for (InventoryCategoryVO i : list) 
			map.put(i.getInventCatSeqno(), i);
	}
	//<1		CD-ROM		CD-R-20161023> InventoryCategoryVO --> 리턴
	public InventoryCategoryVO searchItem(int seq){
		return map.get(seq);
	}
	//<K:1, V:CD-ROM> --> String: CD-ROM 리턴
	public String getInventCategory(int seq){
		return map.get(seq).getInventCatName();
	}
}
