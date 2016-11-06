package com.sqisoft.ssbr.inventory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;

public interface InventoryService {
	
	/**
	 * �옱怨� �벑濡�
	 * @param inventoryInfoVO
	 */
	public void insertInventory(InventoryInfoVO inventoryInfoVO);
	/**
	 * �옱怨� 移댄뀒怨좊━, �옱怨� �젙蹂대벑�쓣 媛��졇���꽌 �꽌鍮꾩뒪 �떆�궓�떎.
	 * @param request
	 * @return
	 */
	public ModelAndView getInventory(HttpServletRequest request);
	/**
	 * �옱怨� 移댄뀒怨좊━ �젙蹂대�� 媛��졇���꽌 �꽌鍮꾩뒪 �떆�궓�떎.
	 * @return
	 */
	public List<InventoryCategoryVO> getInventoryCategory();
	public void deleteInventory(InventoryInfoVO inventoryInfoVO);
	public void updateInventory(InventoryInfoVO inventoryInfoVO);

	
}
