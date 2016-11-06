package com.sqisoft.ssbr.inventory.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.OMGVMCID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.sqisoft.ssbr.inventory.biz.InventoryBiz;
import com.sqisoft.ssbr.inventory.dao.InventoryDAO;
import com.sqisoft.ssbr.inventory.service.InventoryService;
import com.sqisoft.ssbr.inventory.vo.InventoryCategoryVO;
import com.sqisoft.ssbr.inventory.vo.InventoryInfoVO;
import com.sqisoft.ssbr.inventory.vo.InventorySearchVO;
import com.sqisoft.ssbr.util.SearchVO;
import com.sqisoft.ssbr.util.SearchVoFactory;

public class InventoryServiceImpl implements InventoryService{
	
	private InventoryBiz inventoryBiz;
	private InventoryDAO inventoryDAO;
	
	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}

	private static Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
	
	public void setInventoryBiz(InventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
	}

	@Override
	public void insertInventory(InventoryInfoVO inventoryInfoVO) {
		inventoryBiz.insertInventory(inventoryInfoVO);
	}

	@Override
	public ModelAndView getInventory(HttpServletRequest request) {
		
		logger.info("ICV001 Load inventoryCatList list.");
		List<InventoryCategoryVO> inventoryCatList = inventoryBiz.getInventoryCategory();
		
		ModelAndView view = new ModelAndView();
		view.addObject("inventoryCatList", inventoryCatList);
		view.setViewName("main/inventory");
		
		SearchVoFactory svf = new SearchVoFactory();
		SearchVO vo = svf.getSearchVO("InventorySearchVO", request, inventoryDAO);

		view.addObject("inventorySearchVO", ((InventorySearchVO) vo).getInventoryInfoSearchVO());
		view.addObject("inventoryList", ((InventorySearchVO) vo).getList());
		return view;
	}

	public List<InventoryCategoryVO> getInventoryCategory() {
		return inventoryDAO.getInventoryCategory();
	}

	@Override
	public void deleteInventory(InventoryInfoVO inventoryInfoVO) {
		inventoryBiz.deleteInventory(inventoryInfoVO);
		
	}

	@Override
	public void updateInventory(InventoryInfoVO inventoryInfoVO) {
		inventoryBiz.updateInventory(inventoryInfoVO);
		
	}
}
