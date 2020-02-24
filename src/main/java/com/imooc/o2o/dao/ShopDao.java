package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 插入店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	
	/**
	 	* 更新店铺
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
	
	/**
	 * 根据ID查询门店
	 * @param shopId
	 * @return
	 */
	Shop selectByShopId(long shopId);
	
	/**
	 * 更具条件查询店铺
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Shop> selectShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 按照条件查询 符合前台传入的条件的商铺的总数
	 * 
	 * @param shopCondition
	 * @return
	 */
	int selectShopCount(@Param("shopCondition") Shop shopCondition);
}
