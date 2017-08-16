package com.lady.entity;

public class GetAccessTo {
	private int positionID;
	private int authorityID;
	
	public GetAccessTo() {}
	
	public GetAccessTo(int positionID, int authorityID){
		this.setPositionID(positionID);
		this.setAuthorityID(authorityID);
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public int getAuthorityID() {
		return authorityID;
	}

	public void setAuthorityID(int authorityID) {
		this.authorityID = authorityID;
	}
}
