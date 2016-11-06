package com.sqisoft.ssbr.inventory.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqisoft.ssbr.inventory.biz.InventoryBiz;
import com.sqisoft.ssbr.inventory.dao.InventoryDAO;
import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;

public class InventoryBizImpl implements InventoryBiz {

	private InventoryDAO inventoryDAO;
	private static Logger logger = LoggerFactory.getLogger(InventoryBizImpl.class);
	
	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
	
	@Override
	public List<InventoryCategoryVO> getInventoryCategory() {
		return inventoryDAO.getInventoryCategory();
	}

	@Override
	public void insertInventory(InventoryInfoVO inventoryInfoVO) {
		inventoryDAO.insertInventory(inventoryInfoVO);
	}

	@Override
	public void deleteInventory(InventoryInfoVO inventoryInfoVO) {
		inventoryDAO.deleteInventory(inventoryInfoVO);
		
	}

	@Override
	public void updateInventory(InventoryInfoVO inventoryInfoVO) {
		inventoryDAO.updateInventory(inventoryInfoVO);
		
	}

}
