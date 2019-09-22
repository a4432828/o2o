package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BastTest;
import com.imooc.o2o.entity.Area;

public class AreaServiceTest extends BastTest {
	
	@Autowired
	public AreaService areaService;
	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		assertEquals("广州", areaList.get(0).getAreaName());
	}

}
