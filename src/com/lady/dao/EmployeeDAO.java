package com.lady.dao;

import java.util.List;

import com.lady.entity.BADTO;
import com.lady.entity.Employee;
import com.lady.entity.MatchInfoDTO;
import com.lady.entity.PeopleInfoDTO;
import com.lady.entity.TeamLeaderDTO;

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
	public double calTotalCompanyHour();
}
