package com.lady.dao;

import java.util.List;

import com.lady.entity.NewTransferTo;
import com.lady.entity.TransferTo;

public interface TransferToDAO {
	public List<TransferTo> readTransferTo();
	public void insertTransferTo(TransferTo transferTo);
	public void deleteTransferTo(String transferToDate, int employeeId, int counterId);
	public void updateTransferTo(String transferToDate, int employeeId, int counterId, TransferTo transferTo);
	
	public List<NewTransferTo> findTransferToFromEmployeeId(int year, int month, int employeeId);
	public int check(String date, int employeeId);
	public List<NewTransferTo> findTransferToFromEmployeeId2(int year, int month, int employeeId);
	public int findCounter(String date, int employeeId);
}
