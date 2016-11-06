package com.sqisoft.ssbr.inventory.dao;

import java.util.List;

import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoSearchVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;

public interface InventoryDAO {

	/**
	 * 재고 카테고리 정보를 DB단에서 가져오는 로직
	 * @return InventoryCategoryVO
	 */
	public List<InventoryCategoryVO> getInventoryCategory();
	
	public void insertInventory(InventoryInfoVO inventoryInfoVO);
	
	/**
	 * 전체 재고 종류 수를 가져온다.
	 * @param inventoryInfoSearchVO
	 * @return
	 */
	public int getInventoryCount(InventoryInfoSearchVO inventoryInfoSearchVO);
	/**
	 * 전체 재고 수(페이징)을 가져온다.
	 * @param inventoryInfoSearchVO
	 * @return
	 */
	public List<InventoryInfoVO> getInventoryList(InventoryInfoSearchVO inventoryInfoSearchVO);

	public void deleteInventory(InventoryInfoVO inventoryInfoVO);

	public void updateInventory(InventoryInfoVO inventoryInfoVO);

}
