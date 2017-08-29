package com.lady.daoImpl;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lady.dao.*;
import com.lady.entity.*;
import com.lady.factory.DAOFactory;
import com.lady.util.ConvertToLegalNumber;
import com.lady.util.DBConnection2;
import com.lady.util.SomeMethod;

public class MySQLEmployeeDAO implements EmployeeDAO{
//	Connection con = DBConnection2.createConnection();
	private Employee employee;
	
	public List<Employee> readEmployee() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE");
			rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("Emp_Name"), rs.getInt("Emp_Area"), rs.getString("Emp_Account"), rs.getString("Emp_Password"), rs.getInt("Pos_ID"), rs.getInt("Is_Checked"), rs.getInt("Base"), rs.getString("Pay_Method"), rs.getString("Start_Date"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("Allowance"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"), rs.getString("Company"));
				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		return list;
	}
	
	public int insertEmployee(Employee employee) {
		int result = 1;
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			con = DBConnection2.createConnection();
			String employeeName = employee.getEmployeeName();
			int employeeArea = employee.getEmployeeArea();
			String account = employee.getEmployeeAccount();
			String password = employee.getEmployeePassword();
			int positionId = employee.getPositionID();
			int isChecked = employee.getIsChecked();
			
			stmt = con.prepareStatement("select Emp_Name, Emp_Account, Counter_ID from Employee");
			rs = stmt.executeQuery();
			while(rs.next()) {
				String employeeNameDB = rs.getString("Emp_Name"); 
				String accountDB = rs.getString("Emp_Account");
				
				if(employeeName.equals(employeeNameDB) | account.equals(accountDB)) {
					result = 0;
					break;
				}
			}
			if(result != 0) {
				stmt2 = con.prepareStatement("insert into EMPLOYEE (Emp_Name, Emp_Area, Emp_Account, Emp_Password, Pos_ID, Is_Checked, Base, Pay_Method, Start_Date, Performance_Bonus, Education_Bonus, Owner_Bonus, Allowance, Insurance_Minus, Insurance, Company) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt2.setString(1, employeeName);
				stmt2.setInt(2, employeeArea);
				stmt2.setString(3, account);
				stmt2.setString(4, password);
				stmt2.setInt(5, positionId);
				stmt2.setInt(6, isChecked);
				stmt2.setInt(7, employee.getBase());
				stmt2.setString(8, employee.getPayMethod());
				stmt2.setString(9, employee.getStartDate());
				stmt2.setInt(10, employee.getPerformanceBonus());
				stmt2.setInt(11, employee.getEducationBonus());
				stmt2.setInt(12, employee.getOwnerBonus());
				stmt2.setInt(13, employee.getAllowance());
				stmt2.setInt(14, employee.getInsuranceMinus());
				stmt2.setInt(15, employee.getInsurance());
				stmt2.setString(16, employee.getCompany());
				stmt2.execute();

				stmt3 = con.prepareStatement("select Emp_ID from EMPLOYEE order by Emp_ID desc");
				rs2 = stmt3.executeQuery();
				if(rs2.next()) {
					result = rs2.getInt("Emp_ID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {}
			}
			if(stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return result;
		}
	}
	
	public void deleteEmployee(int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from EMPLOYEE where Emp_ID=?");
			stmt.setInt(1, employeeId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void updateEmployee(int employeeId, Employee employee) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update EMPLOYEE set Emp_Name=?, Emp_Account=?, Emp_Password=? WHERE Emp_ID=?");
			stmt.setString(1, employee.getEmployeeName());
			stmt.setString(2, employee.getEmployeeAccount());
			stmt.setString(3, employee.getEmployeePassword());
			stmt.setInt(4, employeeId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public Employee selectEmployee(int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Employee employee = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE where Emp_ID=?");
			stmt.setInt(1, employeeId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				employee = new Employee(rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("Emp_Name"), rs.getInt("Emp_Area"), rs.getString("Emp_Account"), rs.getString("Emp_Password"), rs.getInt("Pos_ID"), rs.getInt("Is_Checked"), rs.getInt("Base"), rs.getString("Pay_Method"), rs.getString("Start_Date"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("Allowance"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"), rs.getString("Company"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return employee;
		}
	}
	
	public String authenticateUser(Employee employee) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String message = null;
		String account = employee.getEmployeeAccount();
		String password = employee.getEmployeePassword();
		
		String accountDB;
		String passwordDB;
		int isChecked;
		int idDB;
		
		try {
			con = DBConnection2.createConnection();
			message = "沒有這個帳戶";
			stmt = con.prepareStatement("select Emp_Account, Emp_Password, Emp_ID, Is_Checked from EMPLOYEE");
			rs = stmt.executeQuery();
			while(rs.next()) {
				accountDB = rs.getString("Emp_Account");
				passwordDB = rs.getString("Emp_Password");
				isChecked = rs.getInt("Is_Checked");
				idDB = rs.getInt("Emp_ID");
				
				if(account.equals(accountDB) && password.equals(passwordDB)) {
					if(isChecked == 0) {
						message = "此帳戶尚未取得開通";
						break;
					}
					else {
						message = String.valueOf(idDB);
						break;
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return message;
		}
	}
	
	public int findCounter(int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int counterID = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Counter_ID from EMPLOYEE where Emp_ID=?");
			stmt.setInt(1, employeeId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counterID = rs.getInt("Counter_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return counterID;
		}
	}
	
	public List<PeopleInfoDTO> findPepleFromUnchecked(int isCheckedId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PeopleInfoDTO peopleInfoDTO;
		List<PeopleInfoDTO> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE,Area where Is_Checked=? and EMPLOYEE.Emp_Area=Area.Area_ID");
			stmt.setInt(1, 0);
			rs = stmt.executeQuery();
			while (rs.next()) {
				peopleInfoDTO = new PeopleInfoDTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), rs.getString("Area_Name"));
				list.add(peopleInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}

	public void updateEmployeeIsChecked(int employeeId, int isCheckedState) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update EMPLOYEE set Is_Checked = ? WHERE Emp_ID=?");
			stmt.setInt(1, isCheckedState);
			stmt.setInt(2, employeeId);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}

	public List<TeamLeaderDTO> findPeopleFromPosition(int positionId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<TeamLeaderDTO> list = new ArrayList<>();
		TeamLeaderDTO teamLeaderDTO;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE,AREA where Pos_ID=? and EMPLOYEE.Emp_Area=AREA.Area_ID");
			stmt.setInt(1, positionId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				teamLeaderDTO = new TeamLeaderDTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), rs.getString("Area_Name"), rs.getString("Emp_Account"), rs.getString("Emp_Password"));
				list.add(teamLeaderDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}
	
	public int findPositionFromPeopleId(int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int positionId = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Pos_ID from EMPLOYEE where Emp_ID=?");
			stmt.setInt(1, employeeId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				positionId = rs.getInt("Pos_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return positionId;
		}
	}
	
	public int insertTeamLeader(Employee employee) {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int result = 1;
		try {
			con = DBConnection2.createConnection();
			String employeeName = employee.getEmployeeName();
			int employeeArea = employee.getEmployeeArea();
			String account = employee.getEmployeeAccount();
			String password = employee.getEmployeePassword();
			int positionId = employee.getPositionID();
			int isChecked = employee.getIsChecked();
			
			stmt = con.prepareStatement("select Emp_Area from EMPLOYEE where Pos_ID=?");
			stmt.setInt(1, 2);
			rs = stmt.executeQuery();
			while(rs.next()) {
				int ariaIdDB = rs.getInt("Emp_Area");
				if(ariaIdDB == employeeArea) {
					result = 0;
					break;
				}
			}
			
			if(result != 0) {
				stmt2 = con.prepareStatement("insert into EMPLOYEE (Emp_Name, Emp_Area, Emp_Account, Emp_Password, Pos_ID, Is_Checked, Base, Pay_Method, Start_Date, Performance_Bonus, Education_Bonus, Owner_Bonus, Allowance, Insurance_Minus, Insurance, Company) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt2.setString(1, employeeName);
				stmt2.setInt(2, employeeArea);
				stmt2.setString(3, account);
				stmt2.setString(4, password);
				stmt2.setInt(5, positionId);
				stmt2.setInt(6, isChecked);
				stmt2.setInt(7, employee.getBase());
				stmt2.setString(8, employee.getPayMethod());
				stmt2.setString(9, employee.getStartDate());
				stmt2.setInt(10, employee.getPerformanceBonus());
				stmt2.setInt(11, employee.getEducationBonus());
				stmt2.setInt(12, employee.getOwnerBonus());
				stmt2.setInt(13, employee.getAllowance());
				stmt2.setInt(14, employee.getInsuranceMinus());
				stmt2.setInt(15, employee.getInsurance());
				stmt2.setString(16, employee.getCompany());
				stmt2.execute();

				stmt3 = con.prepareStatement("select Emp_ID from EMPLOYEE order by Emp_ID desc");
				rs2 = stmt3.executeQuery();
				if(rs2.next()) {
					result = rs2.getInt("Emp_ID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {}
			}
			if(stmt3 != null) {
				try {
					stmt3.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return result;
		}
	}

	public List<MatchInfoDTO> findBA(int areaId, int positionId, int isChecked) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MatchInfoDTO> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
			//FOR MSSQL
//			DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//			FOR MySQL
			DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
			CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
			stmt = con.prepareStatement("select * from EMPLOYEE where Emp_Area=? and Pos_ID=? and Is_Checked=?");
			stmt.setInt(1, areaId);
			stmt.setInt(2, positionId);
			stmt.setInt(3, isChecked);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Counter counter = new Counter();
				if(rs.getInt("Counter_ID") != 0) {
					counter = counterDAO.selectCounter(rs.getInt("Counter_ID"));
				}
				MatchInfoDTO matchInfoDTO = new MatchInfoDTO(calTotalCompanyHour(0), rs.getInt("Emp_ID"), rs.getString("Emp_Name"), counter.getCounterName(), rs.getInt("Counter_ID"));
				list.add(matchInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}

	public void match(int employeeId, int counterId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			if(counterId != 0) {
				stmt = con.prepareStatement("update EMPLOYEE set Counter_ID=? WHERE Emp_ID=?");
				stmt.setInt(1, counterId);
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else {
				stmt = con.prepareStatement("update EMPLOYEE set Counter_ID=? WHERE Emp_ID=?");
				stmt.setString(1, null);
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public List<BADTO> findBAFromAreaId(int areaId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BADTO> list = new ArrayList<>();
		BADTO baDTO;
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE where Pos_ID=1 and Is_Checked=1 and Emp_Area=?");
			stmt.setInt(1, areaId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String areaName = areaDAO.findAreaFromId(areaId);
				baDTO = new BADTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), areaName);
				list.add(baDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}

	public List<Employee> findPeopleFromPosition2(int positionId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>();
		Employee employee;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE where Pos_ID=?");
			stmt.setInt(1, positionId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("Emp_Name"), rs.getInt("Emp_Area"), rs.getString("Emp_Account"), rs.getString("Emp_Password"), rs.getInt("Pos_ID"), rs.getInt("Is_Checked"), rs.getInt("Base"), rs.getString("Pay_Method"), rs.getString("Start_Date"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("Allowance"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"), rs.getString("Company"));
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}

	public List<Employee> findBAPeopleFromAreaId(int areaId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>();
		Employee employee;
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE where Pos_ID=1 and Is_Checked=1 and Emp_Area=?");
			stmt.setInt(1, areaId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee(rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("Emp_Name"), rs.getInt("Emp_Area"), rs.getString("Emp_Account"), rs.getString("Emp_Password"), rs.getInt("Pos_ID"), rs.getInt("Is_Checked"), rs.getInt("Base"), rs.getString("Pay_Method"), rs.getString("Start_Date"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("Allowance"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"), rs.getString("Company"));
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}

	public double calTotalCompanyHour(int areaId) {
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();

		double totalCompanyHour = 0;

		List<Employee> employees;
		if(areaId == 0) {
			 employees = findPeopleFromPosition2(1);
		}
		else {
			employees = findBAPeopleFromAreaId(areaId);
		}

		for(int i=0; i<employees.size(); i++) {
			int counterId = employees.get(i).getCounterID();
			if(counterId != 0) {
				String[] weekdays ={"X", "日","一","二","三","四","五","六"};
				String[] workTime = counterDAO.getWorkTime(counterId);
				double start = 0;
				double end = 0;
				double[] startArr = new double[7];
				double[] endArr = new double[7];
				for (int j=0; j<7; j++) {
					String arr[] = workTime[j].split("-");
					startArr[j] = Double.parseDouble(arr[0]);
					endArr[j] = Double.parseDouble(arr[1]);
				}
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				int dates = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
				for(int j=weekdayIndex; j<weekdayIndex+dates; j++) {
					int k = j % 7;
					if(k == 0)
						k = 7;
					if(weekdays[k].equals("一")) {
						start = startArr[0];
						end = endArr[0];
					}
					else if(weekdays[k].equals("二")) {
						start = startArr[1];
						end = endArr[1];
					}
					else if(weekdays[k].equals("三")) {
						start = startArr[2];
						end = endArr[2];
					}
					else if(weekdays[k].equals("四")) {
						start = startArr[3];
						end = endArr[3];
					}
					else if(weekdays[k].equals("五")) {
						start = startArr[4];
						end = endArr[4];
					}
					else if(weekdays[k].equals("六")) {
						start = startArr[5];
						end = endArr[5];
					}
					else if(weekdays[k].equals("日")) {
						start = startArr[6];
						end = endArr[6];
					}
					totalCompanyHour = totalCompanyHour + (end - start - 1);
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId != 0) {
				Calendar calendar = Calendar.getInstance();
				List<Rest> rests = restDAO.findRestFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<rests.size(); j++) {
					if(rests.get(j).getRestType() != "") {
						double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
						double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
						totalCompanyHour = totalCompanyHour - (end - start - 1);
					}
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId != 0) {
				Calendar calendar = Calendar.getInstance();
				List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<newTransferTos.size(); j++) {
					if(newTransferTos.get(j).getEmployeeId() != 0) {
						double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
						double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
						double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						totalCompanyHour = totalCompanyHour - (end - start - 1);
						totalCompanyHour = totalCompanyHour + (traEnd - traStart - 1);
					}
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId != 0) {
				Calendar calendar = Calendar.getInstance();
				List<Special> specials = specialDAO.findSpecialFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<specials.size(); j++) {
					if(specials.get(j).getEmployeeId() != 0) {
						double start = 0;
						double end = 0;
						int newCounterId = counterId;
						int check = transferToDAO.check(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
						if(check == 1) {
							newCounterId = transferToDAO.findCounter(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
						}
						start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
						end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
						int isSpecial = specialDAO.checkRepeat(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
						if(isSpecial == 1) {
							totalCompanyHour = totalCompanyHour - (end - start - 1);
							double speStart = specialDAO.findOnTime(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
							double speEnd = specialDAO.findOffTime(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
							if(speStart != 0) {
								start = speStart;
							}
							if(speEnd != 0) {
								end = speEnd;
							}
							totalCompanyHour = totalCompanyHour + (end - start);
							if (start <= 11 && end >= 19)
								totalCompanyHour = totalCompanyHour - 1;
							else if (start <= 11 && end >= 17 && end < 19)
								totalCompanyHour = totalCompanyHour - 0.5;
							else if (start <= 11 && end >= 13 && end <= 17)
								totalCompanyHour = totalCompanyHour - 0.5;
							else if (start > 11 && start <= 13 && end >= 19)
								totalCompanyHour = totalCompanyHour - 0.5;
							else if (start >= 13 && start <= 17 && end >= 19)
								totalCompanyHour = totalCompanyHour - 0.5;
						}
					}
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId == 0) {
				Calendar calendar = Calendar.getInstance();
				List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<newTransferTos.size(); j++) {
					if(newTransferTos.get(j).getEmployeeId() != 0) {
						double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						int isSpecial = specialDAO.checkRepeat(newTransferTos.get(j).getTransferToDate(), employees.get(i).getEmployeeID());
						if(isSpecial == 1) {
//							totalCompanyHour = totalCompanyHour - (traEnd - traStart - 1);
							double speStart = specialDAO.findOnTime(newTransferTos.get(j).getTransferToDate(), employees.get(i).getEmployeeID());
							double speEnd = specialDAO.findOffTime(newTransferTos.get(j).getTransferToDate(), employees.get(i).getEmployeeID());
							if(speStart != 0) {
								traStart = speStart;
							}
							if(speEnd != 0) {
								traEnd = speEnd;
							}
							totalCompanyHour = totalCompanyHour + (traEnd - traStart);
							if (traStart <= 11 && traEnd >= 19)
								totalCompanyHour = totalCompanyHour - 1;
							else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
								totalCompanyHour = totalCompanyHour - 0.5;
							else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
								totalCompanyHour = totalCompanyHour - 0.5;
							else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
								totalCompanyHour = totalCompanyHour - 0.5;
							else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
								totalCompanyHour = totalCompanyHour - 0.5;
						}
						else
							totalCompanyHour = totalCompanyHour + (traEnd - traStart - 1);
					}
				}
			}
		}
		return totalCompanyHour;
	}

	//總加班時數
	public double calTotalOvertime(int areaId) {
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();

		List<Employee> employees;
		if(areaId == 0) {
			employees = findPeopleFromPosition2(1);
		}
		else {
			employees = findBAPeopleFromAreaId(areaId);
		}

		double totalOvertime = 0;

		for(int i=0; i<employees.size(); i++) {
			double workHour = 0;
			int counterId = employees.get(i).getCounterID();
			if(counterId != 0) {
				String[] weekdays ={"X", "日","一","二","三","四","五","六"};
				String[] workTime = counterDAO.getWorkTime(counterId);
				double start = 0;
				double end = 0;
				double[] startArr = new double[7];
				double[] endArr = new double[7];
				for (int j=0; j<7; j++) {
					String arr[] = workTime[j].split("-");
					startArr[j] = Double.parseDouble(arr[0]);
					endArr[j] = Double.parseDouble(arr[1]);
				}
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				int dates = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
				for(int j=weekdayIndex; j<weekdayIndex+dates; j++) {
					int k = j % 7;
					if(k == 0)
						k = 7;
					if(weekdays[k].equals("一")) {
						start = startArr[0];
						end = endArr[0];
					}
					else if(weekdays[k].equals("二")) {
						start = startArr[1];
						end = endArr[1];
					}
					else if(weekdays[k].equals("三")) {
						start = startArr[2];
						end = endArr[2];
					}
					else if(weekdays[k].equals("四")) {
						start = startArr[3];
						end = endArr[3];
					}
					else if(weekdays[k].equals("五")) {
						start = startArr[4];
						end = endArr[4];
					}
					else if(weekdays[k].equals("六")) {
						start = startArr[5];
						end = endArr[5];
					}
					else if(weekdays[k].equals("日")) {
						start = startArr[6];
						end = endArr[6];
					}
					workHour = workHour + (end - start - 1);
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId != 0) {
				Calendar calendar = Calendar.getInstance();
				List<Rest> rests = restDAO.findRestFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<rests.size(); j++) {
					if(rests.get(j).getRestType() != "") {
						double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
						double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
						workHour = workHour - (end - start - 1);
					}
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId != 0) {
				Calendar calendar = Calendar.getInstance();
				List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<newTransferTos.size(); j++) {
					if(newTransferTos.get(j).getEmployeeId() != 0) {
						double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
						double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
						double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						workHour = workHour - (end - start - 1);
						workHour = workHour + (traEnd - traStart - 1);
					}
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId != 0) {
				Calendar calendar = Calendar.getInstance();
				List<Special> specials = specialDAO.findSpecialFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<specials.size(); j++) {
					if(specials.get(j).getEmployeeId() != 0) {
						double start = 0;
						double end = 0;
						int newCounterId = counterId;
						int check = transferToDAO.check(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
						if(check == 1) {
							newCounterId = transferToDAO.findCounter(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
						}
						start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
						end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
						int isSpecial = specialDAO.checkRepeat(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
						if(isSpecial == 1) {
							workHour = workHour - (end - start - 1);
							double speStart = specialDAO.findOnTime(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
							double speEnd = specialDAO.findOffTime(specials.get(j).getSpecialDate(), employees.get(i).getEmployeeID());
							if(speStart != 0) {
								start = speStart;
							}
							if(speEnd != 0) {
								end = speEnd;
							}
							workHour = workHour + (end - start);
							if (start <= 11 && end >= 19)
								workHour = workHour - 1;
							else if (start <= 11 && end >= 17 && end < 19)
								workHour = workHour - 0.5;
							else if (start <= 11 && end >= 13 && end <= 17)
								workHour = workHour - 0.5;
							else if (start > 11 && start <= 13 && end >= 19)
								workHour = workHour - 0.5;
							else if (start >= 13 && start <= 17 && end >= 19)
								workHour = workHour - 0.5;
						}
					}
				}
			}
//			System.out.println(totalCompanyHour);

			if(counterId == 0) {
				Calendar calendar = Calendar.getInstance();
				List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employees.get(i).getEmployeeID());
				for(int j=0; j<newTransferTos.size(); j++) {
					if(newTransferTos.get(j).getEmployeeId() != 0) {
						double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
						int isSpecial = specialDAO.checkRepeat(newTransferTos.get(j).getTransferToDate(), employees.get(i).getEmployeeID());
						if(isSpecial == 1) {
//							totalCompanyHour = totalCompanyHour - (traEnd - traStart - 1);
							double speStart = specialDAO.findOnTime(newTransferTos.get(j).getTransferToDate(), employees.get(i).getEmployeeID());
							double speEnd = specialDAO.findOffTime(newTransferTos.get(j).getTransferToDate(), employees.get(i).getEmployeeID());
							if(speStart != 0) {
								traStart = speStart;
							}
							if(speEnd != 0) {
								traEnd = speEnd;
							}
							workHour = workHour + (traEnd - traStart);
							if (traStart <= 11 && traEnd >= 19)
								workHour = workHour - 1;
							else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
								workHour = workHour - 0.5;
							else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
								workHour = workHour - 0.5;
							else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
								workHour = workHour - 0.5;
							else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
								workHour = workHour - 0.5;
						}
						else
							workHour = workHour + (traEnd - traStart - 1);
					}
				}
			}
			Calendar calendar = Calendar.getInstance();
			int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			double maxHour = SomeMethod.convertToNeedHour(days);
			double overtime;
			if((workHour - maxHour) > 0)
				overtime = workHour - maxHour;
			else
				overtime = 0;
			totalOvertime = totalOvertime + overtime;
		}
		return totalOvertime;
	}

	public double calEmployeeOvertime(int employeeId) {
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();

		Employee employee = employeeDAO.selectEmployee(employeeId);
		double employeeOvertime = 0;
		double workHour = 0;

		int counterId = employee.getCounterID();
		if(counterId != 0) {
			String[] weekdays ={"X", "日","一","二","三","四","五","六"};
			String[] workTime = counterDAO.getWorkTime(counterId);
			double start = 0;
			double end = 0;
			double[] startArr = new double[7];
			double[] endArr = new double[7];
			for (int j=0; j<7; j++) {
				String arr[] = workTime[j].split("-");
				startArr[j] = Double.parseDouble(arr[0]);
				endArr[j] = Double.parseDouble(arr[1]);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			int dates = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
			for(int j=weekdayIndex; j<weekdayIndex+dates; j++) {
				int k = j % 7;
				if(k == 0)
					k = 7;
				if(weekdays[k].equals("一")) {
					start = startArr[0];
					end = endArr[0];
				}
				else if(weekdays[k].equals("二")) {
					start = startArr[1];
					end = endArr[1];
				}
				else if(weekdays[k].equals("三")) {
					start = startArr[2];
					end = endArr[2];
				}
				else if(weekdays[k].equals("四")) {
					start = startArr[3];
					end = endArr[3];
				}
				else if(weekdays[k].equals("五")) {
					start = startArr[4];
					end = endArr[4];
				}
				else if(weekdays[k].equals("六")) {
					start = startArr[5];
					end = endArr[5];
				}
				else if(weekdays[k].equals("日")) {
					start = startArr[6];
					end = endArr[6];
				}
				workHour = workHour + (end - start - 1);
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<Rest> rests = restDAO.findRestFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<rests.size(); j++) {
				if(rests.get(j).getRestType() != "") {
					double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
					double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
					workHour = workHour - (end - start - 1);
				}
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<newTransferTos.size(); j++) {
				if(newTransferTos.get(j).getEmployeeId() != 0) {
					double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
					double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
					double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					workHour = workHour - (end - start - 1);
					workHour = workHour + (traEnd - traStart - 1);
				}
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<Special> specials = specialDAO.findSpecialFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<specials.size(); j++) {
				if(specials.get(j).getEmployeeId() != 0) {
					double start = 0;
					double end = 0;
					int newCounterId = counterId;
					int check = transferToDAO.check(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					if(check == 1) {
						newCounterId = transferToDAO.findCounter(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					}
					start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
					end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
					int isSpecial = specialDAO.checkRepeat(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					if(isSpecial == 1) {
						workHour = workHour - (end - start - 1);
						double speStart = specialDAO.findOnTime(specials.get(j).getSpecialDate(), employee.getEmployeeID());
						double speEnd = specialDAO.findOffTime(specials.get(j).getSpecialDate(), employee.getEmployeeID());
						if(speStart != 0) {
							start = speStart;
						}
						if(speEnd != 0) {
							end = speEnd;
						}
						workHour = workHour + (end - start);
						if (start <= 11 && end >= 19)
							workHour = workHour - 1;
						else if (start <= 11 && end >= 17 && end < 19)
							workHour = workHour - 0.5;
						else if (start <= 11 && end >= 13 && end <= 17)
							workHour = workHour - 0.5;
						else if (start > 11 && start <= 13 && end >= 19)
							workHour = workHour - 0.5;
						else if (start >= 13 && start <= 17 && end >= 19)
							workHour = workHour - 0.5;
					}
				}
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId == 0) {
			Calendar calendar = Calendar.getInstance();
			List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<newTransferTos.size(); j++) {
				if(newTransferTos.get(j).getEmployeeId() != 0) {
					double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					int isSpecial = specialDAO.checkRepeat(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
					if(isSpecial == 1) {
//							totalCompanyHour = totalCompanyHour - (traEnd - traStart - 1);
						double speStart = specialDAO.findOnTime(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
						double speEnd = specialDAO.findOffTime(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
						if(speStart != 0) {
							traStart = speStart;
						}
						if(speEnd != 0) {
							traEnd = speEnd;
						}
						workHour = workHour + (traEnd - traStart);
						if (traStart <= 11 && traEnd >= 19)
							workHour = workHour - 1;
						else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
							workHour = workHour - 0.5;
						else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
							workHour = workHour - 0.5;
						else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
							workHour = workHour - 0.5;
						else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
							workHour = workHour - 0.5;
					}
					else
						workHour = workHour + (traEnd - traStart - 1);
				}
			}
		}
		Calendar calendar = Calendar.getInstance();
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		double maxHour = SomeMethod.convertToNeedHour(days);
		double overtime;
		if((workHour - maxHour) > 0)
			overtime = workHour - maxHour;
		else
			overtime = 0;
		employeeOvertime = employeeOvertime + overtime;

		return employeeOvertime;
	}

	public int findEmployeeIdFromEmployeeName(String employeeName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("SELECT * FROM EMPLOYEE where Emp_Name=? AND Pos_ID=1");
			stmt.setString(1, employeeName);
			rs = stmt.executeQuery();
			if(rs.next())
				result = rs.getInt("Emp_ID");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return result;
		}
	}

	public void updateEmployeeStartDate(int employeeId, String startDate) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update EMPLOYEE set Start_Date=? WHERE Emp_ID=?");
			stmt.setString(1, startDate);
			stmt.setInt(2, employeeId);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}

	public List<BADTO> findLostEmployee() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BADTO> employees = new ArrayList<>();
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from EMPLOYEE where Pay_Method='X' AND Pos_ID=1 AND Is_Checked=1");
			rs = stmt.executeQuery();
			if(rs.next()) {
				int areaId = rs.getInt("Emp_Area");
				String areaName = areaDAO.findAreaFromId(areaId);
				BADTO badto = new BADTO(rs.getInt("Emp_ID"), rs.getString("Emp_Name"), areaName);
				employees.add(badto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return employees;
		}
	}

	public void updateBasicInfo(int employeeId, int base, String payMethod, int performanceBonus, int educationBonus, int ownerBonus, int allowance, int insuranceMinus, int insurance, String company) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update EMPLOYEE set Base=?, Pay_Method=?, Performance_Bonus=?, Education_Bonus=?, Owner_Bonus=?, Allowance=?, Insurance_Minus=?, Insurance=?, Company=? WHERE Emp_ID=?");
			stmt.setInt(1, base);
			stmt.setString(2, payMethod);
			stmt.setInt(3, performanceBonus);
			stmt.setInt(4, educationBonus);
			stmt.setInt(5, ownerBonus);
			stmt.setInt(6, allowance);
			stmt.setInt(7, insuranceMinus);
			stmt.setInt(8, insurance);
			stmt.setString(9, company);
			stmt.setInt(10, employeeId);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}

	public double[] calEmployeeBasedOnDayPay(int employeeId) {
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();

		Employee employee = employeeDAO.selectEmployee(employeeId);
		//int[0]代表做滿10小時(不包含吃飯)的天數; int[1]代表沒做滿的零碎小時(視為加班費)
		double[] result = new double[2];
		double fullDay = 0;
		double hour = 0;
		double dailyWorkTime;

		int counterId = employee.getCounterID();
		if(counterId != 0) {
			String[] weekdays ={"X", "日","一","二","三","四","五","六"};
			String[] workTime = counterDAO.getWorkTime(counterId);
			double start = 0;
			double end = 0;
			double[] startArr = new double[7];
			double[] endArr = new double[7];
			for (int j=0; j<7; j++) {
				String arr[] = workTime[j].split("-");
				startArr[j] = Double.parseDouble(arr[0]);
				endArr[j] = Double.parseDouble(arr[1]);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			int dates = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
			for(int j=weekdayIndex; j<weekdayIndex+dates; j++) {
				int k = j % 7;
				if(k == 0)
					k = 7;
				if(weekdays[k].equals("一")) {
					start = startArr[0];
					end = endArr[0];
				}
				else if(weekdays[k].equals("二")) {
					start = startArr[1];
					end = endArr[1];
				}
				else if(weekdays[k].equals("三")) {
					start = startArr[2];
					end = endArr[2];
				}
				else if(weekdays[k].equals("四")) {
					start = startArr[3];
					end = endArr[3];
				}
				else if(weekdays[k].equals("五")) {
					start = startArr[4];
					end = endArr[4];
				}
				else if(weekdays[k].equals("六")) {
					start = startArr[5];
					end = endArr[5];
				}
				else if(weekdays[k].equals("日")) {
					start = startArr[6];
					end = endArr[6];
				}

				dailyWorkTime = end - start;
				if (start <= 11 && end >= 19)
					dailyWorkTime = dailyWorkTime - 1;
				else if (start <= 11 && end >= 17 && end < 19)
					dailyWorkTime = dailyWorkTime - 0.5;
				else if (start <= 11 && end >= 13 && end <= 17)
					dailyWorkTime = dailyWorkTime - 0.5;
				else if (start > 11 && start <= 13 && end >= 19)
					dailyWorkTime = dailyWorkTime - 0.5;
				else if (start >= 13 && start <= 17 && end >= 19)
					dailyWorkTime = dailyWorkTime - 0.5;
				if(dailyWorkTime >= 10 )
					fullDay = fullDay + 1;
				else
					hour = hour + dailyWorkTime;
			}
		}

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<Rest> rests = restDAO.findRestFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<rests.size(); j++) {
				if(rests.get(j).getRestType() != "") {
					double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
					double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
					dailyWorkTime = end - start;
					if (start <= 11 && end >= 19)
						dailyWorkTime = dailyWorkTime - 1;
					else if (start <= 11 && end >= 17 && end < 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (start <= 11 && end >= 13 && end <= 17)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (start > 11 && start <= 13 && end >= 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (start >= 13 && start <= 17 && end >= 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					if(dailyWorkTime >= 10 )
						fullDay = fullDay - 1;
					else
						hour = hour - dailyWorkTime;
				}
			}
		}

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<newTransferTos.size(); j++) {
				if(newTransferTos.get(j).getEmployeeId() != 0) {
					double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
					double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
					double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					dailyWorkTime = end - start;
					if (start <= 11 && end >= 19)
						dailyWorkTime = dailyWorkTime - 1;
					else if (start <= 11 && end >= 17 && end < 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (start <= 11 && end >= 13 && end <= 17)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (start > 11 && start <= 13 && end >= 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (start >= 13 && start <= 17 && end >= 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					if(dailyWorkTime >= 10 )
						fullDay = fullDay - 1;
					else
						hour = hour - dailyWorkTime;
//					workHour = workHour - (end - start - 1);

					dailyWorkTime = traEnd - traStart;
					if (traStart <= 11 && traEnd >= 19)
						dailyWorkTime = dailyWorkTime - 1;
					else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
						dailyWorkTime = dailyWorkTime - 0.5;
					if(dailyWorkTime >= 10 )
						fullDay = fullDay + 1;
					else
						hour = hour + dailyWorkTime;
//					workHour = workHour + (traEnd - traStart - 1);
				}
			}
		}

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<Special> specials = specialDAO.findSpecialFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<specials.size(); j++) {
				if(specials.get(j).getEmployeeId() != 0) {
					double start = 0;
					double end = 0;
					int newCounterId = counterId;
					int check = transferToDAO.check(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					if(check == 1) {
						newCounterId = transferToDAO.findCounter(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					}
					start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
					end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
					int isSpecial = specialDAO.checkRepeat(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					if(isSpecial == 1) {

//						workHour = workHour - (end - start - 1);
						dailyWorkTime = end - start;
						if (start <= 11 && end >= 19)
							dailyWorkTime = dailyWorkTime - 1;
						else if (start <= 11 && end >= 17 && end < 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (start <= 11 && end >= 13 && end <= 17)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (start > 11 && start <= 13 && end >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (start >= 13 && start <= 17 && end >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						if(dailyWorkTime >= 10 )
							fullDay = fullDay - 1;
						else
							hour = hour - dailyWorkTime;

						double speStart = specialDAO.findOnTime(specials.get(j).getSpecialDate(), employee.getEmployeeID());
						double speEnd = specialDAO.findOffTime(specials.get(j).getSpecialDate(), employee.getEmployeeID());
						if(speStart != 0) {
							start = speStart;
						}
						if(speEnd != 0) {
							end = speEnd;
						}
//						workHour = workHour + (end - start);
						dailyWorkTime = end - start;
						if (start <= 11 && end >= 19)
							dailyWorkTime = dailyWorkTime - 1;
						else if (start <= 11 && end >= 17 && end < 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (start <= 11 && end >= 13 && end <= 17)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (start > 11 && start <= 13 && end >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (start >= 13 && start <= 17 && end >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						if(dailyWorkTime >= 10 )
							fullDay = fullDay + 1;
						else
							hour = hour + dailyWorkTime;
					}
				}
			}
		}

		if(counterId == 0) {
			Calendar calendar = Calendar.getInstance();
			List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<newTransferTos.size(); j++) {
				if(newTransferTos.get(j).getEmployeeId() != 0) {
					double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					int isSpecial = specialDAO.checkRepeat(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
					if(isSpecial == 1) {
//							totalCompanyHour = totalCompanyHour - (traEnd - traStart - 1);
						double speStart = specialDAO.findOnTime(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
						double speEnd = specialDAO.findOffTime(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
						if(speStart != 0) {
							traStart = speStart;
						}
						if(speEnd != 0) {
							traEnd = speEnd;
						}
						dailyWorkTime = traEnd - traStart;
						if (traStart <= 11 && traEnd >= 19)
							dailyWorkTime = dailyWorkTime - 1;
						else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						if(dailyWorkTime >= 10 )
							fullDay = fullDay + 1;
						else
							hour = hour + dailyWorkTime;
					}
					else {
//						workHour = workHour + (traEnd - traStart - 1);
						dailyWorkTime = traEnd - traStart;
						if (traStart <= 11 && traEnd >= 19)
							dailyWorkTime = dailyWorkTime - 1;
						else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
							dailyWorkTime = dailyWorkTime - 0.5;
						if(dailyWorkTime >= 10 )
							fullDay = fullDay + 1;
						else
							hour = hour + dailyWorkTime;
					}
				}
			}
		}
		result[0] = fullDay;
		result[1] = hour;
		return result;
	}

	public double calEmployeeBasedOnHourPay(int employeeId) {
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();

		Employee employee = employeeDAO.selectEmployee(employeeId);
		double workHour = 0;

		int counterId = employee.getCounterID();
		if(counterId != 0) {
			String[] weekdays ={"X", "日","一","二","三","四","五","六"};
			String[] workTime = counterDAO.getWorkTime(counterId);
			double start = 0;
			double end = 0;
			double[] startArr = new double[7];
			double[] endArr = new double[7];
			for (int j=0; j<7; j++) {
				String arr[] = workTime[j].split("-");
				startArr[j] = Double.parseDouble(arr[0]);
				endArr[j] = Double.parseDouble(arr[1]);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			int dates = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
			for(int j=weekdayIndex; j<weekdayIndex+dates; j++) {
				int k = j % 7;
				if(k == 0)
					k = 7;
				if(weekdays[k].equals("一")) {
					start = startArr[0];
					end = endArr[0];
				}
				else if(weekdays[k].equals("二")) {
					start = startArr[1];
					end = endArr[1];
				}
				else if(weekdays[k].equals("三")) {
					start = startArr[2];
					end = endArr[2];
				}
				else if(weekdays[k].equals("四")) {
					start = startArr[3];
					end = endArr[3];
				}
				else if(weekdays[k].equals("五")) {
					start = startArr[4];
					end = endArr[4];
				}
				else if(weekdays[k].equals("六")) {
					start = startArr[5];
					end = endArr[5];
				}
				else if(weekdays[k].equals("日")) {
					start = startArr[6];
					end = endArr[6];
				}
				workHour = workHour + (end - start - 1);
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<Rest> rests = restDAO.findRestFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<rests.size(); j++) {
				if(rests.get(j).getRestType() != "") {
					double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
					double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(rests.get(j).getRestDate(), counterId));
					workHour = workHour - (end - start - 1);
				}
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<newTransferTos.size(); j++) {
				if(newTransferTos.get(j).getEmployeeId() != 0) {
					double start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
					double end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), counterId));
					double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					workHour = workHour - (end - start - 1);
					workHour = workHour + (traEnd - traStart - 1);
				}
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId != 0) {
			Calendar calendar = Calendar.getInstance();
			List<Special> specials = specialDAO.findSpecialFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<specials.size(); j++) {
				if(specials.get(j).getEmployeeId() != 0) {
					double start = 0;
					double end = 0;
					int newCounterId = counterId;
					int check = transferToDAO.check(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					if(check == 1) {
						newCounterId = transferToDAO.findCounter(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					}
					start = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
					end = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(specials.get(j).getSpecialDate(), newCounterId));
					int isSpecial = specialDAO.checkRepeat(specials.get(j).getSpecialDate(), employee.getEmployeeID());
					if(isSpecial == 1) {
						workHour = workHour - (end - start - 1);
						double speStart = specialDAO.findOnTime(specials.get(j).getSpecialDate(), employee.getEmployeeID());
						double speEnd = specialDAO.findOffTime(specials.get(j).getSpecialDate(), employee.getEmployeeID());
						if(speStart != 0) {
							start = speStart;
						}
						if(speEnd != 0) {
							end = speEnd;
						}
						workHour = workHour + (end - start);
						if (start <= 11 && end >= 19)
							workHour = workHour - 1;
						else if (start <= 11 && end >= 17 && end < 19)
							workHour = workHour - 0.5;
						else if (start <= 11 && end >= 13 && end <= 17)
							workHour = workHour - 0.5;
						else if (start > 11 && start <= 13 && end >= 19)
							workHour = workHour - 0.5;
						else if (start >= 13 && start <= 17 && end >= 19)
							workHour = workHour - 0.5;
					}
				}
			}
		}
//		System.out.println(totalCompanyHour);

		if(counterId == 0) {
			Calendar calendar = Calendar.getInstance();
			List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employee.getEmployeeID());
			for(int j=0; j<newTransferTos.size(); j++) {
				if(newTransferTos.get(j).getEmployeeId() != 0) {
					double traStart = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					double traEnd = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(newTransferTos.get(j).getTransferToDate(), newTransferTos.get(j).getCounterId()));
					int isSpecial = specialDAO.checkRepeat(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
					if(isSpecial == 1) {
//							totalCompanyHour = totalCompanyHour - (traEnd - traStart - 1);
						double speStart = specialDAO.findOnTime(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
						double speEnd = specialDAO.findOffTime(newTransferTos.get(j).getTransferToDate(), employee.getEmployeeID());
						if(speStart != 0) {
							traStart = speStart;
						}
						if(speEnd != 0) {
							traEnd = speEnd;
						}
						workHour = workHour + (traEnd - traStart);
						if (traStart <= 11 && traEnd >= 19)
							workHour = workHour - 1;
						else if (traStart <= 11 && traEnd >= 17 && traEnd < 19)
							workHour = workHour - 0.5;
						else if (traStart <= 11 && traEnd >= 13 && traEnd <= 17)
							workHour = workHour - 0.5;
						else if (traStart > 11 && traStart <= 13 && traEnd >= 19)
							workHour = workHour - 0.5;
						else if (traStart >= 13 && traStart <= 17 && traEnd >= 19)
							workHour = workHour - 0.5;
					}
					else
						workHour = workHour + (traEnd - traStart - 1);
				}
			}
		}
		return workHour;
	}

	public void modifyBasicInfo(int employeeId, String column, String newValue) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			if(column.equals("payMethod")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Pay_Method=? where Emp_ID=?");
				stmt.setString(1, newValue);
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("base")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Base=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("performanceBonus")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Performance_Bonus=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("educationBonus")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Education_Bonus=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("ownerBonus")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Owner_Bonus=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("allowance")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Allowance=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("insuranceMinus")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Insurance_Minus=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("insurance")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Insurance=? where Emp_ID=?");
				stmt.setInt(1, Integer.valueOf(newValue));
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
			else if(column.equals("company")) {
				stmt = con.prepareStatement("UPDATE EMPLOYEE set Company=? where Emp_ID=?");
				stmt.setString(1, newValue);
				stmt.setInt(2, employeeId);
				stmt.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}

	public List<PaySummaryInfo> findPaySummaryInfo(String time) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PaySummaryInfo> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from PAY_SUMMARY where time=?");
			stmt.setString(1, time);
			rs = stmt.executeQuery();
			while(rs.next()) {
				PaySummaryInfo paySummaryInfo = new PaySummaryInfo(rs.getString("Time"), rs.getString("Emp_Name"), rs.getInt("Base"), rs.getInt("Performance_Bonus"), rs.getInt("Education_Bonus"), rs.getInt("Owner_Bonus"), rs.getInt("allowance"), rs.getInt("bonus"), rs.getInt("Insurance_Minus"), rs.getInt("Insurance"));
				list.add(paySummaryInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return list;
		}
	}
}

