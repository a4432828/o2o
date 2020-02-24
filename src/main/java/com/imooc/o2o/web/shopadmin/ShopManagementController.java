package com.imooc.o2o.web.shopadmin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.OperationStatusEnum;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.services.AreaServices;
import com.imooc.o2o.services.ShopCategoryService;
import com.imooc.o2o.services.ShopService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;


@Service
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaServices areaServices;
	
	/**
	 *获取店铺分类和区域信息 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getshopinitinfo() {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			List<Area> areaList = areaServices.getAreaList();
			
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		
		return modelMap;
	}
	
	/**
	    * 注册店铺
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerShop(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		
		// 校验验证码
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}
		
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse（创建一次，可重用）
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile shopImg = null;
		MultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (MultipartFile) multipartRequest.getFile("shopImg");
		}
		
		if (shopImg == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.PIC_EMPTY.getStateInfo());
			return modelMap;
		}
		if(shop != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se = shopService.addShop(shop, shopImg);
			
			if(se.getState() == ShopStateEnum.CHECK.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOP_INFO.getStateInfo());
			return modelMap;
		}
	}
	
	/**
	 * 根据id获取店铺信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取shopid
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				modelMap.put("shop", shop);
				// 获取区域列表
				List<Area> areaList = areaServices.getAreaList();
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOPID.getStateInfo());
		}
		return modelMap;
	}
	
	/**
	 * 修改店铺
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 校验验证码
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}

		// 1、接收并转化相应参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse（创建一次，可重用）
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 获取图片文件流
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile shopImg = null;
		MultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (MultipartFile) multipartRequest.getFile("shopImg");
		}

		// 2、修改店铺，尽量不要依靠前端信息
		if (shop != null && shop.getShopId() > 0) {
			ShopExecution se = shopService.modifyShop(shop, shopImg);
			if (se.getState() == OperationStatusEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOPID.getStateInfo());
			return modelMap;
		}
	}


}
