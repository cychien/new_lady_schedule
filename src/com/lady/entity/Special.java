package com.lady.entity;

public class Special {
	private String specialDate;
	private int employeeId;
	private int counterId;
	private String onTime;
	private String offTime;
	private int changedBy;
	
	public Special(){}
	
	public Special(String specialDate, int employeeId, int counterId, String onTime, String offTime, int changedBy) {
		super();
		this.specialDate = specialDate;
		this.employeeId = employeeId;
		this.counterId = counterId;
		this.onTime = onTime;
		this.offTime = offTime;
		this.changedBy = changedBy;
	}

	public String getSpecialDate() {
		return specialDate;
	}
	public void setSpecialDate(String specialDate) {
		this.specialDate = specialDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getCounterId() {
		return counterId;
	}
	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}
	public String getOnTime() {
		return onTime;
	}
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}
	public String getOffTime() {
		return offTime;
	}
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}
	public int getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(int changedBy) {
		this.changedBy = changedBy;
	}
	
	
}
