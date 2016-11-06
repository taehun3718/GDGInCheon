package com.sqisoft.ssbr.util;

import javax.servlet.http.HttpServletRequest;

import com.sqisoft.ssbr.inventory.dao.InventoryDAO;
import com.sqisoft.ssbr.inventory.vo.InventorySearchVO;
import com.sqisoft.ssbr.member.dao.MemberDAO;
import com.sqisoft.ssbr.member.vo.MemberSearchVO;

public class SearchVoFactory {
	public SearchVO getSearchVO(String searchVoType
							, HttpServletRequest request
							, Object dao) {
		if (searchVoType == null) {
			return null;
		}
		if (searchVoType.equalsIgnoreCase("EnginnerSearchVO")) {
			return new MemberSearchVO(request, (MemberDAO) dao);
		}
		if (searchVoType.equalsIgnoreCase("InventorySearchVO")) {
			return new InventorySearchVO(request, (InventoryDAO) dao);
		}
		return null;
	}
}
