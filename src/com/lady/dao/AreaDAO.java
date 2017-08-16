package com.lady.dao;

import java.util.List;

import com.lady.entity.Area;

public interface AreaDAO {
	public List<Area> readArea();
	public int insertArea(Area area);
	public void deleteArea(int areaId);
	public void updateArea(int areaId, Area area);
	
	public int findAreaFromName(String areaName);
	public String findAreaFromId(int areaId);
	public List<Area> findAreaFromEmployeeName(String employeName);
}
