package com.imooc.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.services.ShopService;


public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testShopAdd() throws ShopOperationException, IOException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
	
		owner.setUserId(8L);
		shopCategory.setShopCategoryId(12L);
		area.setAreaId(3L);
		
		shop.setShopName("mytest6");
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
		String filePath = "D:\\image_200x300.jpg";
		
		ShopExecution se = shopService.addShop(shop, path2MultipartFile(filePath));
		
	}
	
	/**
	 * filePath to MultipartFile
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	private MultipartFile path2MultipartFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));
		return multipartFile;
	}
}
