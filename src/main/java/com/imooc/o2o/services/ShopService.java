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

}
