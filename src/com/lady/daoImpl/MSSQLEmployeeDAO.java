package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lady.dao.AreaDAO;
import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.*;
import com.lady.factory.DAOFactory;
import com.lady.util.DBConnection;

public class MSSQLEmployeeDAO implements EmployeeDAO{
	Connection con = DBConnection.createConnection();
	private Employee employee;
	
	public List<Employee> readEmployee() {
		List<Employee> list = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[EMPLOYEE]");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("Emp_Name"), rs.getInt("Emp_Area"), rs.getString("Emp_Account"), rs.getString("Emp_Passeord"), rs.getInt("Pos_ID"), rs.getInt("Is_Checked"), rs.getInt("Base"), rs.getString("Pay_Method"), rs.getString("Start_Date"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("Allowance"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"), rs.getString("Company"));
				list.add(employee);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertEmployee(Employee employee) {
		try {
			String employeeName = employee.getEmployeeName();
			int employeeArea = employee.getEmployeeArea();
			String account = employee.getEmployeeAccount();
//			int counterId = employee.getCounterID();
			String password = employee.getEmployeePassword();
			int positionId = employee.getPositionID();
			int isChecked = employee.getIsChecked();
			
			int employeeId = 0;
			
			PreparedStatement stmt = con.prepareStatement("select Emp_Name, Emp_Account, Counter_ID from [dbo].[Employee]");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String employeeNameDB = rs.getString("Emp_Name"); 
				String accountDB = rs.getString("Emp_Account");
				
				if(employeeName.equals(employeeNameDB) | account.equals(accountDB)) {
					return 0;
				}
			}
			
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			
			PreparedStatement stmt2 = con.prepareStatement("insert into [dbo].[EMPLOYEE] (Emp_Name, Emp_Area, Emp_Account, Emp_Password, Pos_ID, Is_Checked) values (?,?,?,?,?,?)");
			stmt2.setString(1, employeeName);
			stmt2.setInt(2, employeeArea);
			stmt2.setString(3, account);
			stmt2.setString(4, password);
			stmt2.setInt(5, positionId);
			stmt2.setInt(6, isChecked);
			stmt2.execute();
			if(stmt2 != null) {
				stmt2.close();
			}
			
			PreparedStatement stmt3 = con.prepareStatement("select Emp_ID from [dbo].[EMPLOYEE] order by Emp_ID desc");
			ResultSet rs2 = stmt3.executeQuery();
			if(rs2.next()) {
				employeeId = rs2.getInt("Emp_ID");
			}
			if (rs2 != null) {
				rs2.close();
			}
			if(stmt3 != null) {
				stmt3.close();
			}
			return employeeId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deleteEmployee(int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from [dbo].[EMPLOYEE] where Emp_ID=?");
			stmt.setInt(1, employeeId);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void updateEmployee(int employeeId, Employee employee) {
		try {
			PreparedStatement stmt = con.prepareStatement("update [dbo].[EMPLOYEE] set Emp_Name=?, Emp_Account=?, Emp_Password=? WHERE Emp_ID=?");
			stmt.setString(1, employee.getEmployeeName());
			stmt.setString(2, employee.getEmployeeAccount());
			stmt.setString(3, employee.getEmployeePassword());
			stmt.setInt(4, employeeId);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Employee selectEmployee(int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[EMPLOYEE] where Emp_ID=?");
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				employee = new Employee(rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("Emp_Name"), rs.getInt("Emp_Area"), rs.getString("Emp_Account"), rs.getString("Emp_Password"), rs.getInt("Pos_ID"), rs.getInt("Is_Checked"), rs.getInt("Base"), rs.getString("Pay_Method"), rs.getString("Start_Date"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("Allowance"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"), rs.getString("Company"));
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			return employee;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String authenticateUser(Employee employee) {
		String account = employee.getEmployeeAccount();
		String password = employee.getEmployeePassword();
		
		String accountDB;
		String passwordDB;
		int isChecked;
		int idDB;
		
		try {
			PreparedStatement stmt = con.prepareStatement("select Emp_Account, Emp_Password, Emp_ID, Is_Checked from [dbo].[EMPLOYEE]");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountDB = rs.getString("Emp_Account");
				passwordDB = rs.getString("Emp_Password");
				isChecked = rs.getInt("Is_Checked");
				idDB = rs.getInt("Emp_ID");
				
				if(account.equals(accountDB) && password.equals(passwordDB)) {
					if(rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if(isChecked == 0) 
						return "此帳戶尚未取得開通";
					else
						return String.valueOf(idDB);
				}
			}
			if(rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "沒有這個帳戶";
	}
	
	public int findCounter(int employeeId) {
		try {
			int counterID;
			PreparedStatement stmt = con.prepareStatement("select Counter_ID from [dbo].[EMPLOYEE] where Emp_ID=?");
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				counterID = rs.getInt("Counter_ID");
				if(rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				return counterID;
			}
			if(rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<PeopleInfoDTO> findPepleFromUnchecked(int isCheckedId) {
		PeopleInfoDTO peopleInfoDTO;
		List<PeopleInfoDTO> list = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[EMPLOYEE],[dbo].[Area] where Is_Checked=? and [dbo].[EMPLOYEE].[Emp_Area]=[dbo].[Area].[Area_ID]");
			stmt.setInt(1, 0);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				peopleInfoDTO = new PeopleInfoDTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), rs.getString("Area_Name"));
				list.add(peopleInfoDTO);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateEmployeeIsChecked(int employeeId, int isCheckedState) {
		try {
			PreparedStatement stmt = con.prepareStatement("update [dbo].[EMPLOYEE] set Is_Checked = ? WHERE Emp_ID=?");
			stmt.setInt(1, isCheckedState);
			stmt.setInt(2, employeeId);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TeamLeaderDTO> findPeopleFromPosition(int positionId) {
		List<TeamLeaderDTO> list = new ArrayList<>();
		TeamLeaderDTO teamLeaderDTO;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[EMPLOYEE],[dbo].[AREA] where Pos_ID=? and [dbo].[EMPLOYEE].[Emp_Area]=[dbo].[AREA].[Area_ID]");
			stmt.setInt(1, positionId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				teamLeaderDTO = new TeamLeaderDTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), rs.getString("Area_Name"), rs.getString("Emp_Account"), rs.getString("Emp_Password"));
				list.add(teamLeaderDTO);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int findPositionFromPeopleId(int employeeId) {
		try {
			int positionId = 0;
			PreparedStatement stmt = con.prepareStatement("select Pos_ID from [dbo].[EMPLOYEE] where Emp_ID=?");
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				positionId = rs.getInt("Pos_ID");
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			return positionId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertTeamLeader(Employee employee) {
		try {
			String employeeName = employee.getEmployeeName();
			int employeeArea = employee.getEmployeeArea();
			String account = employee.getEmployeeAccount();
//			int counterId = employee.getCounterID();
			String password = employee.getEmployeePassword();
			int positionId = employee.getPositionID();
			int isChecked = employee.getIsChecked();
			
			int employeeId = 0;
			
			PreparedStatement stmt = con.prepareStatement("select Emp_Area from [dbo].[EMPLOYEE] where Pos_ID=?");
			stmt.setInt(1, 2);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int ariaIdDB = rs.getInt("Emp_Area");
				
				if(ariaIdDB == employeeArea) {
//					PreparedStatement stmt2 = con.prepareStatement("select Emp_Name, Emp_Account, Emp_Password from [dbo].[EMPLOYEE]");
//					ResultSet rs2 = stmt2.executeQuery();
//					while(rs2.next()) {
//						String employeeNameDB = rs.getString("Emp_Name");
//						String employeeAccountDB = rs.getString("Emp_Account");
//						String employeePasswordDB = rs.getString("Emp_Password");
//						if(employeeNameDB.equals(employeeName) && (employeeAccountDB.equals(account) | employeePasswordDB.equals(password)))
//					}
					return 0;
				}
			}
			
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			
			PreparedStatement stmt2 = con.prepareStatement("insert into [dbo].[EMPLOYEE] (Emp_Name, Emp_Area, Emp_Account, Emp_Password, Pos_ID, Is_Checked) values (?,?,?,?,?,?)");
			stmt2.setString(1, employeeName);
			stmt2.setInt(2, employeeArea);
			stmt2.setString(3, account);
			stmt2.setString(4, password);
			stmt2.setInt(5, positionId);
			stmt2.setInt(6, isChecked);
			stmt2.execute();
			if(stmt2 != null) {
				stmt2.close();
			}
			
			PreparedStatement stmt3 = con.prepareStatement("select Emp_ID from [dbo].[EMPLOYEE] order by Emp_ID desc");
			ResultSet rs2 = stmt3.executeQuery();
			if(rs2.next()) {
				employeeId = rs2.getInt("Emp_ID");
			}
			if (rs2 != null) {
				rs2.close();
			}
			if(stmt3 != null) {
				stmt3.close();
			}
			return employeeId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<MatchInfoDTO> findBA(int areaId, int positionId, int isChecked) {
		List<MatchInfoDTO> list = new ArrayList<>();
		try {
			DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
			CounterDAO counterDAO = MSSQLDAOFactory.getCounterDAO();
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[EMPLOYEE] where Emp_Area=? and Pos_ID=? and Is_Checked=?");
			stmt.setInt(1, areaId);
			stmt.setInt(2, positionId);
			stmt.setInt(3, isChecked);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Counter counter = new Counter();
				if(rs.getInt("Counter_ID") != 0) {
					counter = counterDAO.selectCounter(rs.getInt("Counter_ID"));
				}
				MatchInfoDTO matchInfoDTO = new MatchInfoDTO(calTotalCompanyHour(0), rs.getInt("Emp_ID"), rs.getString("Emp_Name"), counter.getCounterName(), rs.getInt("Counter_ID"));
				list.add(matchInfoDTO);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void match(int employeeId, int counterId) {
		try {
			if(counterId != 0) {
				PreparedStatement stmt = con.prepareStatement("update [dbo].[EMPLOYEE] set Counter_ID=? WHERE Emp_ID=?");
				stmt.setInt(1, counterId);
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else {
				PreparedStatement stmt = con.prepareStatement("update [dbo].[EMPLOYEE] set Counter_ID=? WHERE Emp_ID=?");
				stmt.setString(1, null);
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public List<BADTO> findBAFromAreaId(int areaId) {
		List<BADTO> list = new ArrayList<>();
		BADTO baDTO;
		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
		AreaDAO areaDAO = MSSQLDAOFactory.getAreaDAO();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[EMPLOYEE] where Pos_ID=1 and Is_Checked=1 and Emp_Area=?");
			stmt.setInt(1, areaId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String areaName = areaDAO.findAreaFromId(areaId);
				baDTO = new BADTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), areaName);
				list.add(baDTO);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Employee> findPeopleFromPosition2(int positionId) {
		return null;
	}
	public List<Employee> findBAPeopleFromAreaId(int areaId){ return null;}
	public double calTotalCompanyHour(int areaId) {
		return 0;
	}
	public double calTotalOvertime(int areaId) {return 0;}
	public double calEmployeeOvertime(int employeeId) {return 0;}
	public int findEmployeeIdFromEmployeeName(String employeeName) {return 0;}
	public void updateEmployeeStartDate(int employeeId, String startDate){};
	public List<BADTO> findLostEmployee() {return null;}
	public void updateBasicInfo(int employeeId, int base, String payMethod, int performanceBonus, int educationBonus, int ownerBonus, int allowance, int insuranceMinus, int insurance, String company){};
	public double[] calEmployeeBasedOnDayPay(int employeeId) {return null;}
	public double calEmployeeBasedOnHourPay(int employeeId) {return 0;}
	public void modifyBasicInfo(int employeeId, String column, String newValue){}
	public List<PaySummaryInfo> findPaySummaryInfo(int year, int month){return null;}
}

