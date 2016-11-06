package com.sqisoft.ssbr.inventory.biz;

import java.util.List;

import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;

public interface InventoryBiz {

	public List<InventoryCategoryVO> getInventoryCategory();
	
	public void insertInventory(InventoryInfoVO inventoryInfoVO);

	public void deleteInventory(InventoryInfoVO inventoryInfoVO);

	public void updateInventory(InventoryInfoVO inventoryInfoVO);

}
