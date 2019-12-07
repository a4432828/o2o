package com.imooc.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;

public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void TestAreaDao() {
		List<Area> queryArea = areaDao.queryArea();
		for (Area area : queryArea) {
			System.out.println(area.getAreaName());
		}
	}
}
