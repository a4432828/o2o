package com.imooc.o2o.services;

import java.util.List;

import com.imooc.o2o.entity.Area;

public interface AreaServices {
	/**
	 * 用来获取区域列表,将区域信息放入缓存中
	 * @return
	 */
	public List<Area> getAreaList() throws Exception;
}
