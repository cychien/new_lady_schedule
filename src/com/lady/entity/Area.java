package com.lady.entity;

public class Area {
	private int areaId;
	private String areaName;
	private String areaRange;
	
	public Area(){}
	
	public Area(int areaId, String areaName, String areaRange){
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaRange = areaRange;
	}
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaRange() {
		return areaRange;
	}
	public void setAreaRange(String areaRange) {
		this.areaRange = areaRange;
	}
}
