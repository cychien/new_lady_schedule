package com.lady.dao;

import java.util.List;

import com.lady.entity.*;

public interface EmployeeDAO {
	public List<Employee> readEmployee();
	public int insertEmployee(Employee employee);
	public void deleteEmployee(int id);
	public void updateEmployee(int employeeId, Employee employee);
	
	public Employee selectEmployee(int employeeId);
	public String authenticateUser(Employee employee);
	public int findCounter(int employeeId);
	public List<PeopleInfoDTO> findPepleFromUnchecked(int isCheckedId);
	public void updateEmployeeIsChecked(int employeeId, int isCheckedState);
	public List<TeamLeaderDTO> findPeopleFromPosition(int positionId);
	public int findPositionFromPeopleId(int employeeId);
	public int insertTeamLeader(Employee employee);
	public List<MatchInfoDTO> findBA(int areaId, int positionId, int isChecked);
	public void match(int employeeId, int counterId);
	public List<BADTO> findBAFromAreaId(int areaId);
	public List<Employee> findPeopleFromPosition2(int positionId);
	public List<Employee> findBAPeopleFromAreaId(int areaId);
	public double calTotalCompanyHour(int areaId);
	public double calTotalOvertime(int areaId);
	public double calEmployeeOvertime(int employeeId);
	public int findEmployeeIdFromEmployeeName(String employeeName);
	public void updateEmployeeStartDate(int employeeId, String startDate);
	public List<BADTO> findLostEmployee();
	public void updateBasicInfo(int employeeId, int base, String payMethod, int performanceBonus, int educationBonus, int ownerBonus, int allowance, int insuranceMinus, int insurance, String company);
	public double[] calEmployeeBasedOnDayPay(int employeeId);
	public double calEmployeeBasedOnHourPay(int employeeId);
	public void modifyBasicInfo(int employeeId, String column, String newValue);
	public List<PaySummaryInfo> findPaySummaryInfo(int year, int month);
}
