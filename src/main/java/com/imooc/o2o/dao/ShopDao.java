package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 插入店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	
	/**
	 * 
	 * 用来更新店铺信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
