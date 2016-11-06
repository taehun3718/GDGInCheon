package com.sqisoft.ssbr.inventory.dao.impl;

import java.util.Collections;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.sqisoft.ssbr.inventory.dao.InventoryDAO;
import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoSearchVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;
import com.sqisoft.ssbr.member.vo.EngineerVO;

public class InventoryDAOImpl extends SqlSessionDaoSupport implements InventoryDAO{

	@Override
	public List<InventoryCategoryVO> getInventoryCategory() {
		return getSqlSession().selectList("inventoryDAO.inventoryCatetoryList");
	}

	@Override
	public void insertInventory(InventoryInfoVO inventoryInfoVO) {
		getSqlSession().insert("inventoryDAO.insertInventory", inventoryInfoVO);
		
	}

	@Override
	public int getInventoryCount(InventoryInfoSearchVO inventoryInfoSearchVO) {
		return getSqlSession().selectOne("inventoryDAO.inventoryCount", inventoryInfoSearchVO);
	}

	@Override
	public List<InventoryInfoVO> getInventoryList(InventoryInfoSearchVO inventoryInfoSearchVO) {
		List<InventoryInfoVO> tmp = getSqlSession().selectList("inventoryDAO.inventoryListPage", inventoryInfoSearchVO);
		return tmp;
	}

	@Override
	public void deleteInventory(InventoryInfoVO inventoryInfoVO) {
		getSqlSession().update("inventoryDAO.inventoryDelete", inventoryInfoVO);
	}

	@Override
	public void updateInventory(InventoryInfoVO inventoryInfoVO) {
		getSqlSession().update("inventoryDAO.inventoryUpdate", inventoryInfoVO);
		
	}

}
