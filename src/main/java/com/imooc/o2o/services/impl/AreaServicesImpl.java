package com.imooc.o2o.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.services.AreaServices;


@Service
public class AreaServicesImpl implements AreaServices {
	
	@Autowired
	private AreaDao areaDao; 

	@Override
	public List<Area> getAreaList() throws Exception {
		List<Area> queryArea = areaDao.queryArea();
		return queryArea;
	}

}
