package com.lady.entity;

public class NewTransferTo {
	private String transferToDate;
	private int employeeId;
	private String counterName;
	private int counterId;
	private String addedBy;
	private String onTime;
	private String offTime;
	
	public NewTransferTo(){}
	
	public NewTransferTo(String transferToDate, int employeeId, String counterName, int counterId, String addedBy, String onTime, String offTime) {
		super();
		this.transferToDate = transferToDate;
		this.employeeId = employeeId;
		this.counterName = counterName;
		this.counterId = counterId;
		this.addedBy = addedBy;
		this.setOnTime(onTime);
		this.setOffTime(offTime);
	}
	
	public String getTransferToDate() {
		return transferToDate;
	}
	public void setTransferToDate(String transferToDate) {
		this.transferToDate = transferToDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public int getCounterId() {
		return counterId;
	}
	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
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
}
