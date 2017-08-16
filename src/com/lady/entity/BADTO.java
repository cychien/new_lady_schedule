package com.lady.entity;

public class BADTO {
	private int employeeId;
	private String employeeName;
	private String areaName;
	
	public BADTO(){}
	
	public BADTO(int employeeId, String employeeName, String areaName) {
		this.setEmployeeId(employeeId);
		this.setEmployeeName(employeeName);
		this.areaName = areaName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
