package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BastTest;
import com.imooc.o2o.entity.Area;

public class AreaDaoTest extends BastTest {
	@Autowired
	public AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> queryArea = areaDao.queryArea();
		assertEquals(2, queryArea.size());
		
//		for (Iterator iterator = queryArea.iterator(); iterator.hasNext();) {
//			Area area = (Area) iterator.next();
//			System.out.println();
//			
//		}
	}
}
