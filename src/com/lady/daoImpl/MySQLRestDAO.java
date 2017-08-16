package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lady.dao.RestDAO;
import com.lady.entity.Rest;
import com.lady.util.DBConnection2;

public class MySQLRestDAO implements RestDAO{
	private Rest rest;

	public int insertRest(Rest rest) {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		int restId = 0;
		try {
			con = DBConnection2.createConnection();
			
			if(rest.getChangedBy() != 0) {
				stmt = con.prepareStatement("insert into REST (Rest_Date, Rest_Type, Rest_Description, Emp_ID, Is_Changed, Changed_By) values (?, ?, ?, ?, ?, ?)");
				stmt.setString(1, rest.getRestDate());
				stmt.setString(2, rest.getRestType());
				stmt.setString(3, rest.getRestDescription());
				stmt.setInt(4, rest.getEmployeeID());
				stmt.setInt(5, rest.getIsChanged()); 
				stmt.setInt(6, rest.getChangedBy());
				stmt.execute();
			}
			else {
				stmt = con.prepareStatement("insert into REST (Rest_Date, Rest_Type, Rest_Description, Emp_ID, Is_Changed) values (?, ?, ?, ?, ?)");
				stmt.setString(1, rest.getRestDate());
				stmt.setString(2, rest.getRestType());
				stmt.setString(3, rest.getRestDescription());
				stmt.setInt(4, rest.getEmployeeID());
				stmt.setInt(5, rest.getIsChanged()); 
				stmt.execute();
			}
			
			stmt2 = con.prepareStatement("select Rest_ID from REST order by Rest_ID desc");
			rs = stmt2.executeQuery();
			if(rs.next()) {
				restId = rs.getInt("Rest_ID");
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
			if(stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return restId;
		}
	}
	
	public void deleteRest(int restId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from REST where Rest_ID=?");
			stmt.setInt(1, restId);
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
	
	public void updateRest(int restId, Rest rest) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update REST set Rest_Date=? and Rest_Type=? and Rest_Description=? and Is_Changed=? and Changed_By=? WHERE Rest_ID=?");
			stmt.setString(1, rest.getRestDate());
			stmt.setString(2, rest.getRestType());
			stmt.setString(3, rest.getRestDescription());
			stmt.setInt(4, restId);
			stmt.setInt(5, rest.getIsChanged());
			stmt.setInt(6, rest.getChangedBy());
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
	
//	public List<Rest> findRestFromEmployeeId(int employeeId) {
//		List<Rest> list = new ArrayList<Rest>();
//		try {
//			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[REST] where Emp_ID=?");
//			stmt.setInt(1, employeeId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				rest = new Rest(rs.getInt("Rest_ID"), rs.getString("Rest_Date"), rs.getString("Rest_Type"), rs.getString("Rest_Description"), rs.getInt("Emp_ID"));
//				list.add(rest);
//			}
//			if(rs != null) {
//				rs.close();
//			}
//			if(stmt != null) {
//				stmt.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		return list;
//	}
	
	public List<Rest> findRestFromEmployeeId(int year, int month, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Rest> list = new ArrayList<Rest>();
		try {
			con = DBConnection2.createConnection();
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
				rest = new Rest(0, "", "", "", 0, 0, 0);
				list.add(rest);
			}
			
			for(int i=space; i<space+totalDate; i++) {
				if(counter < 10) {
					queryDate = "0" + String.valueOf(counter);
				}
				else {
					queryDate = String.valueOf(counter);
				}
				stmt = con.prepareStatement("select * from rest where Rest_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					rest = new Rest(0, rs.getString("Rest_Date"), rs.getString("Rest_Type"), rs.getString("Rest_Description"), rs.getInt("Emp_ID"), rs.getInt("Is_Changed"), rs.getInt("Changed_By"));
					list.add(rest);
				}
				else {
					rest = new Rest(0, queryYearAndMonth + queryDate, "", "", 0, 0, 0);
					list.add(rest);
				}
				counter++;
			}
			
			for(int i=space+totalDate; i<42; i++) {
				Rest rest = new Rest(0, "", "", "", 0, 0, 0);
				list.add(rest);
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

	public int findRestId(int employeeId, String date) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int restId = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Rest_ID from REST where Emp_ID=? and Rest_Date=?");
			stmt.setInt(1, employeeId);
			stmt.setString(2, date);
			rs = stmt.executeQuery();
			if(rs.next()) {
				restId = rs.getInt("Rest_ID");
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
			return restId;
		}
	}
	
	public List<Rest> findRestFromEmployeeId2(int year, int month, int employeeId){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Rest> list = new ArrayList<Rest>();
		try {
			con = DBConnection2.createConnection();
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
				stmt = con.prepareStatement("select * from rest where Rest_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					rest = new Rest(0, rs.getString("Rest_Date"), rs.getString("Rest_Type"), rs.getString("Rest_Description"), rs.getInt("Emp_ID"), rs.getInt("Is_Changed"), rs.getInt("Changed_By"));
					list.add(rest);
				}
				else {
					rest = new Rest(0, queryYearAndMonth + queryDate, "", "", 0, 0, 0);
					list.add(rest);
				}
				counter++;
			}
		} catch(SQLException e) {
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
	
	public void changeIsChanged(int restId, int whoChange) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update REST set Is_Changed=? and Changed_By=? WHERE Rest_ID=?");
			stmt.setInt(1, 1);
			stmt.setInt(2, whoChange);
			stmt.setInt(3, restId);
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
}
