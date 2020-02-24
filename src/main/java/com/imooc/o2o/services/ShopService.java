package com.imooc.o2o.services;

import org.springframework.web.multipart.MultipartFile;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;


public interface ShopService {
	/**
	 * 添加店铺
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution addShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;
	
	/**
	 * 根据ID获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 修改店铺
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution modifyShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;
	
	
	/**
	 * 获取店铺分页列表
	 * 
	 * @param shopCondition 店铺查询条件
	 * @param pageIndex     第几页
	 * @param pageSize      每页条数
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException;

}
