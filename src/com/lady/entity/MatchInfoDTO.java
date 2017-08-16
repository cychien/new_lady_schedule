package com.lady.entity;

public class MatchInfoDTO {
	private double totalCompanyHour;
	private int employeeId;
	private String employeeName;
	private int counterId;
	private String employeeCounter;
	public MatchInfoDTO() {}
	public MatchInfoDTO(double totalCompanyHour, int employeeId, String employeeName, String employeeCounter, int counterId) {
		super();
		this.totalCompanyHour = totalCompanyHour;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCounter = employeeCounter;
		this.counterId = counterId;
	}

	public double getTotalCompanyHour() { return totalCompanyHour; }
	public void setTotalCompanyHour(double totalCompanyHour) { this.totalCompanyHour = totalCompanyHour; }
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
	public String getEmployeeCounter() {
		return employeeCounter;
	}
	public void setEmployeeCounter(String employeeCounter) {
		this.employeeCounter = employeeCounter;
	}
	public int getCounterId() {
		return counterId;
	}
	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}
	
}
