package com.sqisoft.ssbr.inventory.dao;

import java.util.List;

import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoSearchVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;

public interface InventoryDAO {

	/**
	 * ��� ī�װ� ������ DB�ܿ��� �������� ����
	 * @return InventoryCategoryVO
	 */
	public List<InventoryCategoryVO> getInventoryCategory();
	
	public void insertInventory(InventoryInfoVO inventoryInfoVO);
	
	/**
	 * ��ü ��� ���� ���� �����´�.
	 * @param inventoryInfoSearchVO
	 * @return
	 */
	public int getInventoryCount(InventoryInfoSearchVO inventoryInfoSearchVO);
	/**
	 * ��ü ��� ��(����¡)�� �����´�.
	 * @param inventoryInfoSearchVO
	 * @return
	 */
	public List<InventoryInfoVO> getInventoryList(InventoryInfoSearchVO inventoryInfoSearchVO);

	public void deleteInventory(InventoryInfoVO inventoryInfoVO);

	public void updateInventory(InventoryInfoVO inventoryInfoVO);

}
