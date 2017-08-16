package com.lady.dao;

import java.util.List;

import com.lady.entity.Rest;;

public interface RestDAO {
//	public List<Rest> readRest(int );
	public int insertRest(Rest rest);
	public void deleteRest(int restId);
	public void updateRest(int restId, Rest rest);
	
	public List<Rest> findRestFromEmployeeId(int year, int month, int employeeId);
	public int findRestId(int employeeId, String date);
	public List<Rest> findRestFromEmployeeId2(int year, int month, int employeeId);
	public void changeIsChanged(int restId, int whoChange);
}
