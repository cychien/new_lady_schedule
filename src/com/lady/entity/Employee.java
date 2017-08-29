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

	private int base;
	private String payMethod;
	private String startDate;
	private int performanceBonus;
	private int educationBonus;
	private int ownerBonus;
	private int allowance;
	private int insuranceMinus;
	private int insurance;
	private String company;
	
	public Employee() {}

	public Employee(int employeeID, int counterID, String employeeName, int employeeArea, String employeeAccount, String employeePassword, int positionID, int isChecked, int base, String payMethod, String startDate, int performanceBonus, int educationBonus, int ownerBonus, int allowance, int insuranceMinus, int insurance, String company) {
		this.employeeID = employeeID;
		this.counterID = counterID;
		this.employeeName = employeeName;
		this.employeeArea = employeeArea;
		this.employeeAccount = employeeAccount;
		this.employeePassword = employeePassword;
		this.positionID = positionID;
		this.isChecked = isChecked;
		this.base = base;
		this.payMethod = payMethod;
		this.startDate = startDate;
		this.performanceBonus = performanceBonus;
		this.educationBonus = educationBonus;
		this.ownerBonus = ownerBonus;
		this.allowance = allowance;
		this.insuranceMinus = insuranceMinus;
		this.insurance = insurance;
		this.company = company;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getPerformanceBonus() {
		return performanceBonus;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public void setPerformanceBonus(int performanceBonus) {
		this.performanceBonus = performanceBonus;
	}

	public int getEducationBonus() {
		return educationBonus;
	}

	public void setEducationBonus(int educationBonus) {
		this.educationBonus = educationBonus;
	}

	public int getOwnerBonus() {
		return ownerBonus;
	}

	public void setOwnerBonus(int ownerBonus) {
		this.ownerBonus = ownerBonus;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	public int getInsuranceMinus() {
		return insuranceMinus;
	}

	public void setInsuranceMinus(int insuranceMinus) {
		this.insuranceMinus = insuranceMinus;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
