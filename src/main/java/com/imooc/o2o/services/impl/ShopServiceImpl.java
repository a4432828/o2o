package com.imooc.o2o.services.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.EnableStatusEnum;
import com.imooc.o2o.enums.OperationStatusEnum;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.services.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;




@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	public ShopExecution addShop(Shop shop, MultipartFile shopImg) throws ShopOperationException {
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		} else {
			try {
				shop.setCreateTime(new Date());
				shop.setEnableStatus(EnableStatusEnum.CHECK.getState());
				
				int effectedNum = shopDao.insertShop(shop);
				if(effectedNum <= 0) {
					throw new ShopOperationException(ShopStateEnum.EDIT_ERROR.getStateInfo());
				} else {
					try {
						if(shopImg == null) {
							throw new ShopOperationException(OperationStatusEnum.PIC_EMPTY.getStateInfo());
						} else {
							addImage(shop, shopImg);
							effectedNum = shopDao.updateShop(shop);
							if (effectedNum <= 0) {
								throw new ShopOperationException(OperationStatusEnum.PIC_UPLOAD_ERROR.getStateInfo());
							}
						}
					} catch (Exception e) {
						throw new ShopOperationException(ShopStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
					}
				}
			} catch (Exception e) {
				throw new ShopOperationException(ShopStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 存储图片
	 * 
	 * @param shop
	 * @param shopImg
	 */
	private void addImage(Shop shop, MultipartFile shopImg) {
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		// 将图片路径存储用于更新店铺信息
		shop.setShopImg(shopImgAddr);
	}

}
