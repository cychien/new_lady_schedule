package com.lady.entity;

public class PeopleInfoDTO {
	private int employeeId;
	private String employeeName;
	private String employeeArea;
	
	public PeopleInfoDTO(){}
	
	public PeopleInfoDTO(int employeeId, String employeeName, String employeeArea) {
		this.setEmployeeId(employeeId);
		this.setEmployeeName(employeeName);
		this.setEmployeeArea(employeeArea);
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeArea() {
		return employeeArea;
	}

	public void setEmployeeArea(String employeeArea) {
		this.employeeArea = employeeArea;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
