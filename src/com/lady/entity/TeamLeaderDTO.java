package com.lady.entity;

public class TeamLeaderDTO {
	private int teamLeaderId;
	private String teamLeaderName;
	private String teamLeaderArea;
	private String teamLeaderAccount;
	private String teamLeaderPassword;
	
	public TeamLeaderDTO(){}
	
	public TeamLeaderDTO(int teamLeaderId,String teamLeaderName, String teamLeaderArea, String teamLeaderAccount, String teamLeaderPassword) {
		this.teamLeaderId = teamLeaderId;
		this.setTeamLeaderName(teamLeaderName);
		this.setTeamLeaderArea(teamLeaderArea);
		this.setTeamLeaderAccount(teamLeaderAccount);
		this.setTeamLeaderPassword(teamLeaderPassword);
	}

	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
	}

	public String getTeamLeaderArea() {
		return teamLeaderArea;
	}

	public void setTeamLeaderArea(String teamLeaderArea) {
		this.teamLeaderArea = teamLeaderArea;
	}

	public String getTeamLeaderAccount() {
		return teamLeaderAccount;
	}

	public void setTeamLeaderAccount(String teamLeaderAccount) {
		this.teamLeaderAccount = teamLeaderAccount;
	}

	public String getTeamLeaderPassword() {
		return teamLeaderPassword;
	}

	public void setTeamLeaderPassword(String teamLeaderPassword) {
		this.teamLeaderPassword = teamLeaderPassword;
	}

	public int getTeamLeaderId() {
		return teamLeaderId;
	}

	public void setTeamLeaderId(int teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}
}
