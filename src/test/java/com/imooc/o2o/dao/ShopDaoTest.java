package com.imooc.o2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testShopInsert() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
	
		owner.setUserId(8L);
		shopCategory.setShopCategoryId(12L);
		area.setAreaId(3L);
		
		shop.setShopName("mytest777777777777777");
		shop.setShopDesc("mytest666");
		shop.setShopAddr("testaddr55666");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setOwner(owner);
		int insertShop = shopDao.insertShop(shop);
		
		System.out.println(insertShop);
	}
	
	@Test
	public void testupdateShop() {
		Shop shop = new Shop();
		shop.setShopId(29L);
		shop.setShopDesc("我来了");
		
		shopDao.updateShop(shop);
	}
}
