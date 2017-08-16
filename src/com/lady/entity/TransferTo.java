package com.lady.entity;

public class TransferTo {
	private String transferToDate;
	private int employeeId;
	private int counterId;
	private int addedBy;
	
	public TransferTo() {}
	
	public TransferTo(String transferToDate, int employeeId, int counterId, int addedBy) {
		this.setTransferToDate(transferToDate);
		this.setEmployeeId(employeeId);
		this.setCounterId(counterId);
		this.setAddedBy(addedBy);
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

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	public int getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}
}
