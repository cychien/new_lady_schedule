package com.lady.dao;

import java.util.List;

import com.lady.entity.Special;

public interface SpecialDAO {
	public void insertSpecial(Special special);
	public void deleteSpecial(String date, int employeeId);
	
	public int checkRepeat(String date, int employeeId);
	public List<Special> findSpecialFromEmployeeId(int year, int month, int employeeId);
	public List<Special> findSpecialFromEmployeeId2(int year, int month, int employeeId);
	public  double findOnTime(String date, int employeeId);
	public  double findOffTime(String date, int employeeId);
}
