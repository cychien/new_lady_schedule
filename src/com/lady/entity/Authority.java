package com.lady.entity;

public class Authority {
	private int authorityID;
	private String authorityName;
	
	public Authority() {}
	
	public Authority(int authorityID, String authorityName){
		this.authorityID = authorityID;
		this.authorityName = authorityName;
	}
	
	public int getAuthorityID() {
		return authorityID;
	}
	public void setAuthorityID(int authorityID) {
		this.authorityID = authorityID;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
}
