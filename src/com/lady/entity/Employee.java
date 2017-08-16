package com.lady.entity;

public class Employee {
	private int employeeID;
	private int counterID;
	private String employeeName;
	private int employeeArea;
	private String employeeAccount;
	private String employeePassword;
	private int positionID;
	private int isChecked;
	
	public Employee() {}
	
	public Employee(int employeeID, int counterID, String employeeName, int employeeArea, String employeeAccount, String employeePassword, int positionID, int isChecked){
		this.employeeID = employeeID;
		this.counterID = counterID;
		this.employeeName = employeeName;
		this.employeeArea = employeeArea;
		this.employeeAccount = employeeAccount;
		this.employeePassword = employeePassword;
		this.positionID = positionID;
		this.isChecked = isChecked;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAccount() {
		return employeeAccount;
	}

	public void setEmployeeAccount(String employeeAccount) {
		this.employeeAccount = employeeAccount;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public int getCounterID() {
		return counterID;
	}

	public void setCounterID(int counterID) {
		this.counterID = counterID;
	}

	public int getEmployeeArea() {
		return employeeArea;
	}

	public void setEmployeeArea(int employeeArea) {
		this.employeeArea = employeeArea;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
}
