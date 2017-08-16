package com.lady.entity;

public class Rest {
	private int restId;
	private String restDate;
	private String restType;
	private String restDescription;
	private int employeeID;
	private int isChanged;
	private int changedBy;
	
	public Rest() {}
	
	public Rest(int restId, String restDate, String restType, String restDescription, int employeeID, int isChanged, int changedBy) {
		this.setRestId(restId);
		this.setRestDate(restDate);
		this.setRestType(restType);
		this.setRestDescription(restDescription);
		this.setEmployeeID(employeeID);
		this.isChanged = isChanged;
		this.changedBy = changedBy;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public String getRestDate() {
		return restDate;
	}

	public void setRestDate(String restDate) {
		this.restDate = restDate;
	}

	public String getRestType() {
		return restType;
	}

	public void setRestType(String restType) {
		this.restType = restType;
	}

	public String getRestDescription() {
		return restDescription;
	}

	public void setRestDescription(String restDescription) {
		this.restDescription = restDescription;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(int isChanged) {
		this.isChanged = isChanged;
	}

	public int getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(int changedBy) {
		this.changedBy = changedBy;
	}
}
