package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lady.dao.SpecialDAO;
import com.lady.entity.Special;
import com.lady.util.DBConnection2;

//**記得加con = DBConnection2.createConnection();**

public class MySQLSpecialDAO implements SpecialDAO{

	public void insertSpecial(Special special) {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		try {
			con = DBConnection2.createConnection();
			stmt2 = con.prepareStatement("select * from SPECIAL where Spe_Date=? and Emp_ID=?");
			stmt2.setString(1, special.getSpecialDate());
			stmt2.setInt(2, special.getEmployeeId());
			rs = stmt2.executeQuery();
			if(rs.next()) {
			    if(!special.getOnTime().equals("")) {
			    	stmt3 = con.prepareStatement("update SPECIAL set OnTime=? where Spe_Date=? and Emp_ID=?");
			    	stmt3.setString(1, special.getOnTime());
			    	stmt3.setString(2, special.getSpecialDate());
			    	stmt3.setInt(3, special.getEmployeeId());
			    	stmt3.execute();
			    }
				if(!special.getOffTime().equals("")) {
			    	PreparedStatement stmt4 = con.prepareStatement("update SPECIAL set OffTime=? where Spe_Date=? and Emp_ID=?");
			    	stmt4.setString(1, special.getOffTime());
			    	stmt4.setString(2, special.getSpecialDate());
			    	stmt4.setInt(3, special.getEmployeeId());
			    	stmt4.execute();
				}
			}
			else {
				System.out.println("else");
				stmt = con.prepareStatement("insert into SPECIAL (Spe_Date, Emp_ID, OnTime, OffTime, Changed_By) values (?,?,?,?,?)");
				stmt.setString(1, special.getSpecialDate());
				stmt.setInt(2, special.getEmployeeId());
				stmt.setString(3, special.getOnTime());
				stmt.setString(4, special.getOffTime());
				stmt.setInt(5, special.getChangedBy());
				stmt.execute();
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
		}
	}
	
	public void deleteSpecial(String date, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from SPECIAL where Spe_Date=? and Emp_ID=?");
			stmt.setString(1, date);
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
	
	public int checkRepeat(String date, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from SPECIAL where Spe_Date=? and Emp_ID=?");
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
	
	public List<Special> findSpecialFromEmployeeId(int year, int month, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Special> list = new ArrayList<>();
		Special special;
		try {
			con = DBConnection2.createConnection();

			String[] monthsNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1);
			int totalDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			String queryYearAndMonth = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + monthsNumber[calendar.get(Calendar.MONTH)] + "-";
			String queryDate;
			int counter = 1; //日期計數
			stmt = null;
			
			for(int i=0; i<totalDate; i++) {
				if(counter < 10) {
					queryDate = "0" + String.valueOf(counter);
				}
				else {
					queryDate = String.valueOf(counter);
				}
				stmt = con.prepareStatement("select * from SPECIAL where Spe_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					special = new Special(rs.getString("Spe_Date"), rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("OnTime"), rs.getString("OffTime"), rs.getInt("Changed_By"));
					list.add(special);
				}
				else {
					special = new Special(queryYearAndMonth + queryDate, 0, 0, "", "", 0);
					list.add(special);
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
	
	public List<Special> findSpecialFromEmployeeId2(int year, int month, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Special> list = new ArrayList<Special>();
		Special special;
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
				special = new Special("", 0, 0, "", "", 0);
				list.add(special);
			}
			
			for(int i=space; i<space+totalDate; i++) {
				if(counter < 10) {
					queryDate = "0" + String.valueOf(counter);
				}
				else {
					queryDate = String.valueOf(counter);
				}
				stmt = con.prepareStatement("select * from SPECIAL where Spe_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					special = new Special(rs.getString("Spe_Date"), rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("OnTime"), rs.getString("OffTime"), rs.getInt("Changed_By"));
					list.add(special);
				}
				else {
					special = new Special(queryYearAndMonth + queryDate, 0, 0, "", "", 0);
					list.add(special);
				}
				counter++;
			}
			for(int i=space+totalDate; i<42; i++) {
				special = new Special("", 0, 0, "", "", 0);
				list.add(special);
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

	public double findOnTime(String date, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		double onTime = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("SELECT  * FROM SPECIAL where Spe_Date=? and Emp_ID=?");
			stmt.setString(1, date);
			stmt.setInt(2, employeeId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(!rs.getString("OnTime").equals(""))
					onTime = Double.parseDouble(rs.getString("OnTime"));
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
			return onTime;
		}
	}

	public double findOffTime(String date, int employeeId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		double offTime = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("SELECT  * FROM SPECIAL where Spe_Date=? and Emp_ID=?");
			stmt.setString(1, date);
			stmt.setInt(2, employeeId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(!rs.getString("OffTime").equals(""))
					offTime = Double.parseDouble(rs.getString("OffTime"));
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
			return offTime;
		}
	}
}
