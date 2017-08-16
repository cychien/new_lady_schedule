package com.lady.entity;

public class AllShiftInfoDTO {
	private MatchInfoDTO matchInfoDTO;
	private Rest[] restArray;
	private NewTransferTo[] transferArray;
	private String[] onTime;
	private String[] offTime;
	private String[] weekday;
	private Special[] specialArray;
	
	public AllShiftInfoDTO(){}
	
	public AllShiftInfoDTO(MatchInfoDTO matchInfoDTO, Rest[] restArray, NewTransferTo[] transferToArray, String[] onTime, String[] offTime, String[] weekday, Special[] specialArray) {
		super();
		this.setMatchInfoDTO(matchInfoDTO);
		this.restArray = restArray;
		this.setTransferArray(transferToArray);
		this.setOnTime(onTime);
		this.setOffTime(offTime);
		this.setWeekday(weekday);
		this.setSpecialArray(specialArray);
	}

	public Rest[] getRestArray() {
		return restArray;
	}
	public void setRestArray(Rest[] restArray) {
		this.restArray = restArray;
	}

	public MatchInfoDTO getMatchInfoDTO() {
		return matchInfoDTO;
	}

	public void setMatchInfoDTO(MatchInfoDTO matchInfoDTO) {
		this.matchInfoDTO = matchInfoDTO;
	}

	public NewTransferTo[] getTransferArray() {
		return transferArray;
	}

	public void setTransferArray(NewTransferTo[] transferArray) {
		this.transferArray = transferArray;
	}

	public String[] getOnTime() {
		return onTime;
	}

	public void setOnTime(String[] onTime) {
		this.onTime = onTime;
	}

	public String[] getWeekday() {
		return weekday;
	}

	public void setWeekday(String[] weekday) {
		this.weekday = weekday;
	}

	public String[] getOffTime() {
		return offTime;
	}

	public void setOffTime(String[] offTime) {
		this.offTime = offTime;
	}
	
	public Special[] getSpecialArray() {
		return specialArray;
	}
	
	public void setSpecialArray(Special[] specialArray) {
		this.specialArray = specialArray;
	}
}
