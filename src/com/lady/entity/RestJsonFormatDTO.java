package com.lady.entity;

public class RestJsonFormatDTO {
	private String month;
	private String date;
	private String type;
	private String description;
	private String transferToCounter;
	private String addedBy;
	private String changedBy;
	private String specialOnTime;
	private String specialOffTime;
	private String restChangedBy;
	
	public RestJsonFormatDTO(){}
	
	public RestJsonFormatDTO(String month, String date, String type, String description, String transferToCounter, String addedBy, String changedBy, String specialOnTime, String specialOffTime, String restChangedBy) {
		this.month = month;
		this.date = date;
		this.type = type;
		this.description = description;
		this.transferToCounter = transferToCounter;
		this.addedBy = addedBy;
		this.changedBy = changedBy;
		this.specialOnTime = specialOnTime;
		this.specialOffTime = specialOffTime;
		this.restChangedBy = restChangedBy;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransferToCounter() {
		return transferToCounter;
	}

	public void setTransferToCounter(String transferToCounter) {
		this.transferToCounter = transferToCounter;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public String getSpecialOnTime() {
		return specialOnTime;
	}

	public void setSpecialOnTime(String specialOnTime) {
		this.specialOnTime = specialOnTime;
	}

	public String getSpecialOffTime() {
		return specialOffTime;
	}

	public void setSpecialOffTime(String specialOffTime) {
		this.specialOffTime = specialOffTime;
	}

	public String getRestChangedBy() {
		return restChangedBy;
	}

	public void setRestChangedBy(String restChangedBy) {
		this.restChangedBy = restChangedBy;
	}
}
