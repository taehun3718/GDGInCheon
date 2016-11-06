package com.sqisoft.ssbr.inventory.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.inventory.service.InventoryService;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;
import com.sqisoft.ssbr.util.Attribute;
import com.sqisoft.ssbr.util.InventoryCategoryHashMap;

@Controller
public class InventoryController {

	private InventoryService inventoryService;
	private static Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	
	/**
	 * 재고 리스트를 가져온다. 
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssBizInventoryList")
	public ModelAndView ssBizInventory(HttpServletRequest request) {
		
		logger.info("ICV002 Load ssBizInventory list.");
		ModelAndView view = inventoryService.getInventory(request);
		view.addObject("MemberVO"
				, request.getSession().getAttribute(Attribute.MEMBER_ATTR));
		return view;
		
	}
	/**
	 * 재고를 등록한다.
	 * @param inventoryInfoVO - 사용자단에서 입력한 정보들(재고 이름/수량 등등)
	 * @param request
	 * @return
	 */
	@RequestMapping("/ssBizInventoryList/doRegistInventory")
	public ModelAndView doRegistInventory(@Valid InventoryInfoVO inventoryInfoVO
										, HttpServletRequest request) {

		// 카테고리에서 불러온 데이터를 HashMap으로 넣는다. 
		// 카테고리의 정보는 InventoryCategory에 있기 때문에 카테고리 정보를 불러와서 
		// seq에 해당하는 카테고리 범주 이름을 찾아 inventoryInfoVO에 다시 넣는다.
		// 
		// 사용자 단 : Category 범주1을 선택했을 때
		// 1	CD-ROM	CD-R-20161023   ===> <1, CD-ROM> ==> 카테고리 범주는 CD-ROM
		// 2	DVD-ROM	DVD-R-20161023  ===> <2, DVD-ROM>
		//
		
		InventoryCategoryHashMap ichm = new InventoryCategoryHashMap(inventoryService.getInventoryCategory());
		inventoryInfoVO.setInventCategory(
				ichm.getInventCategory(Integer.parseInt(inventoryInfoVO.getInventCategory())) );
		
		logger.info("ICV003 Load inventoryCatList list.");
		logger.info("ICR000 Do Regist inventory." 
		+ inventoryInfoVO.getInventName()
		+ "/" + inventoryInfoVO.getInventCategory()
		+ "/" + inventoryInfoVO.getInventMemo());
		
		inventoryService.insertInventory(inventoryInfoVO);
		
		return new ModelAndView("redirect:/ssBizInventoryList");
	}
	
	@RequestMapping("/ssBizInventoryList/doDeleteInventory")
	public ModelAndView doDeleteInventory(@Valid InventoryInfoVO inventoryInfoVO) {
		
		logger.info("ICR001 Do Delete inventory. seq:" + inventoryInfoVO.getInventSeqno());
		inventoryService.deleteInventory(inventoryInfoVO);
		return new ModelAndView("redirect:/ssBizInventoryList");
	}
	
	@RequestMapping("/ssBizInventoryList/doUpdateInventory")
	public ModelAndView doUpdateInventory(@Valid InventoryInfoVO inventoryInfoVO) {
		
		logger.info("ICR002 Do Update inventory. seq:" + inventoryInfoVO.getInventSeqno());
		inventoryService.updateInventory(inventoryInfoVO);
		return new ModelAndView("redirect:/ssBizInventoryList");
	}
	
	
	

}
