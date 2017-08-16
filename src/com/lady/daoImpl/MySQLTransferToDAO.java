package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.dao.TransferToDAO;
import com.lady.entity.NewTransferTo;
import com.lady.entity.TransferTo;
import com.lady.factory.DAOFactory;
import com.lady.util.DBConnection2;

public class MySQLTransferToDAO implements TransferToDAO{
	TransferTo transferTo = new TransferTo();
	
	public List<TransferTo> readTransferTo() {
		return null;
	}

	public void deleteTransferTo(String transferToDate, int employeeId, int counterId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from TRANSFER_TO where Tra_Date=? and Emp_ID=?");
			stmt.setString(1, transferToDate);
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

	public void insertTransferTo(TransferTo transferTo) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("insert into TRANSFER_TO (Tra_Date, Emp_ID, Counter_ID, Added_By) values (?,?,?,?)");
			stmt.setString(1, transferTo.getTransferToDate());
			stmt.setInt(2, transferTo.getEmployeeId());
			stmt.setInt(3, transferTo.getCounterId());
			stmt.setInt(4, transferTo.getAddedBy());
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

	public void updateTransferTo(String transferToDate, int employeeId, int counterId, TransferTo transferTo) {}

	public List<NewTransferTo> findTransferToFromEmployeeId(int year, int month, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<NewTransferTo> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
//			FOR MSSQL
//			DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//			FOR MySQL
			DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
			CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
			EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
			String[] monthsNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1);
			int totalDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			String queryYearAndMonth = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + monthsNumber[calendar.get(Calendar.MONTH)] + "-";
			String queryDate;
			int counter = 1; //日期計數
			
			for(int i=0; i<totalDate; i++) {
				if(counter < 10) {
					queryDate = "0" + String.valueOf(counter);
				}
				else {
					queryDate = String.valueOf(counter);
				}
				stmt = con.prepareStatement("select * from TRANSFER_TO where Tra_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					transferTo = new TransferTo(rs.getString("Tra_Date"), rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getInt("Added_By"));
					NewTransferTo newTransferTo = new NewTransferTo();
					newTransferTo.setTransferToDate(transferTo.getTransferToDate());
					newTransferTo.setEmployeeId(transferTo.getEmployeeId());
					newTransferTo.setCounterId(transferTo.getCounterId());
					String counterName = counterDAO.findCounterName(transferTo.getCounterId());
					newTransferTo.setCounterName(counterName);
					String addedByName = employeeDAO.selectEmployee(rs.getInt("Added_By")).getEmployeeName();
					newTransferTo.setAddedBy(addedByName);
					String onTime = counterDAO.findOnTimeFromDateAndId(transferTo.getTransferToDate(), transferTo.getCounterId());
					String offTime = counterDAO.findOffTimeFromDateAndId(transferTo.getTransferToDate(), transferTo.getCounterId());
					newTransferTo.setOnTime(onTime);
					newTransferTo.setOffTime(offTime);
					list.add(newTransferTo);
				}
				else {
					transferTo = new TransferTo(queryYearAndMonth + queryDate, 0, 0, 0);
					NewTransferTo newTransferTo = new NewTransferTo();
					newTransferTo.setTransferToDate(transferTo.getTransferToDate());
					newTransferTo.setEmployeeId(transferTo.getEmployeeId());
					newTransferTo.setCounterId(transferTo.getCounterId());
					newTransferTo.setCounterName("");
					newTransferTo.setAddedBy("");
					newTransferTo.setOnTime("");
					newTransferTo.setOffTime("");
					list.add(newTransferTo);
				}
				counter++;
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
			return list;
		}
	}
	
	public List<NewTransferTo> findTransferToFromEmployeeId2(int year, int month, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<NewTransferTo> list = new ArrayList<>();
		NewTransferTo newTransferTo2 = new NewTransferTo();
		try {
			con = DBConnection2.createConnection();
//			FOR MSSQL
//			DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//			FOR MySQL
			DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
			CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
			EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
			String[] monthsNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1);
			int totalDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int firstDate = calendar.get(Calendar.DAY_OF_WEEK);
			int space = firstDate - 1;
			String queryYearAndMonth = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + monthsNumber[calendar.get(Calendar.MONTH)] + "-";
			String queryDate;
			int counter = 1; //日期計數
			
			for(int i=0; i<space; i++) {
				newTransferTo2 = new NewTransferTo("", 0, "", 0, "", "", "");
				list.add(newTransferTo2);
			}
			
			for(int i=space; i<space+totalDate; i++) {
				if(counter < 10) {
					queryDate = "0" + String.valueOf(counter);
				}
				else {
					queryDate = String.valueOf(counter);
				}
				stmt = con.prepareStatement("select * from TRANSFER_TO where Tra_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					transferTo = new TransferTo(rs.getString("Tra_Date"), rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getInt("Added_By"));
					NewTransferTo newTransferTo = new NewTransferTo();
					newTransferTo.setTransferToDate(transferTo.getTransferToDate());
					newTransferTo.setEmployeeId(transferTo.getEmployeeId());
					newTransferTo.setCounterId(transferTo.getCounterId());
					String counterName = counterDAO.findCounterName(transferTo.getCounterId());
					newTransferTo.setCounterName(counterName);
					String addedByName = employeeDAO.selectEmployee(rs.getInt("Added_By")).getEmployeeName();
					newTransferTo.setAddedBy(addedByName);
					String onTime = counterDAO.findOnTimeFromDateAndId(transferTo.getTransferToDate(), transferTo.getCounterId());
					String offTime = counterDAO.findOffTimeFromDateAndId(transferTo.getTransferToDate(), transferTo.getCounterId());
					newTransferTo.setOnTime(onTime);
					newTransferTo.setOffTime(offTime);
					list.add(newTransferTo);
				}
				else {
					transferTo = new TransferTo(queryYearAndMonth + queryDate, 0, 0, 0);
					NewTransferTo newTransferTo = new NewTransferTo();
					newTransferTo.setTransferToDate(transferTo.getTransferToDate());
					newTransferTo.setEmployeeId(transferTo.getEmployeeId());
					newTransferTo.setCounterId(transferTo.getCounterId());
					newTransferTo.setCounterName("");
					newTransferTo.setAddedBy("");
					newTransferTo.setOnTime("");
					newTransferTo.setOffTime("");
					list.add(newTransferTo);
				}
				counter++;
			}
			for(int i=space+totalDate; i<42; i++) {
				newTransferTo2 = new NewTransferTo("", 0, "", 0, "", "", "");
				list.add(newTransferTo2);
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
			return list;
		}
	}
	
	public int check(String date, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from TRANSFER_TO where Tra_Date=? and Emp_ID=?");
			stmt.setString(1, date);
			stmt.setInt(2, employeeId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = 1;
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
			return result;
		}
	}

	public int findCounter(String date, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int counterId = 0;
		try{
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from TRANSFER_TO where Tra_Date=? and Emp_ID=?");
			stmt.setString(1, date);
			stmt.setInt(2, employeeId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counterId = rs.getInt("Counter_ID");
			}
		}catch (SQLException e) {
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
			return counterId;
		}
	}
}
