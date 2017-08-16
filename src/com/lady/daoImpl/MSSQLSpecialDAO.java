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
import com.lady.util.DBConnection;

public class MSSQLSpecialDAO implements SpecialDAO{
	Connection con = DBConnection.createConnection();
	public void insertSpecial(Special special) {
		try {
			PreparedStatement stmt2 = con.prepareStatement("select * from [dbo].[SPECIAL] where Spe_Date=? and Emp_ID=?");
			stmt2.setString(1, special.getSpecialDate());
			stmt2.setInt(2, special.getEmployeeId());
			ResultSet rs = stmt2.executeQuery();
			if(rs.next()) {
			    if(!special.getOnTime().equals("")) {
			    	PreparedStatement stmt3 = con.prepareStatement("update [dbo].[SPECIAL] set OnTime=? where Spe_Date=? and Emp_ID=?");
			    	stmt3.setString(1, special.getOnTime());
			    	stmt3.setString(2, special.getSpecialDate());
			    	stmt3.setInt(3, special.getEmployeeId());
			    	stmt3.execute();
			    	if(stmt3 != null)
					    stmt3.close();
			    }
				if(!special.getOffTime().equals("")) {
			    	PreparedStatement stmt4 = con.prepareStatement("update [dbo].[SPECIAL] set OffTime=? where Spe_Date=? and Emp_ID=?");
			    	stmt4.setString(1, special.getOffTime());
			    	stmt4.setString(2, special.getSpecialDate());
			    	stmt4.setInt(3, special.getEmployeeId());
			    	stmt4.execute();
			    	if(stmt4 != null)
					    stmt4.close();
				}
			}
			else {
				System.out.println("else");
				PreparedStatement stmt = con.prepareStatement("insert into [dbo].[SPECIAL] (Spe_Date, Emp_ID, OnTime, OffTime, Changed_By) values (?,?,?,?,?)");
				stmt.setString(1, special.getSpecialDate());
				stmt.setInt(2, special.getEmployeeId());
				stmt.setString(3, special.getOnTime());
				stmt.setString(4, special.getOffTime());
				stmt.setInt(5, special.getChangedBy());
				stmt.execute();
				
				if(stmt != null) 
					stmt.close();
			}
			if(rs != null)
				rs.close();
			if(stmt2 != null)
				stmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSpecial(String date, int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from [dbo].[SPECIAL] where Spe_Date=? and Emp_ID=?");
			stmt.setString(1, date);
			stmt.setInt(2, employeeId);
			stmt.execute();
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int checkRepeat(String date, int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[SPECIAL] where Spe_Date=? and Emp_ID=?");
			stmt.setString(1, date);
			stmt.setInt(2, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			if(stmt != null) 
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Special> findSpecialFromEmployeeId(int year, int month, int employeeId) {
		List<Special> list = new ArrayList<>();
		Special special;
		try {
			String[] monthsNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1);
			int totalDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			String queryYearAndMonth = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + monthsNumber[calendar.get(Calendar.MONTH)] + "-";
			String queryDate;
			int counter = 1; //日期計數
			PreparedStatement stmt;
			
			for(int i=0; i<totalDate; i++) {
				if(counter < 10) {
					queryDate = "0" + String.valueOf(counter);
				}
				else {
					queryDate = String.valueOf(counter);
				}
				stmt = con.prepareStatement("select * from [dbo].[SPECIAL] where Spe_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					special = new Special(rs.getString("Spe_Date"), rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("OnTime"), rs.getString("OffTime"), rs.getInt("Changed_By"));
					list.add(special);
				}
				else {
					special = new Special(queryYearAndMonth + queryDate, 0, 0, "", "", 0);
					list.add(special);
				}
				if(rs != null) {
					rs.close();
				}
				counter++;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Special> findSpecialFromEmployeeId2(int year, int month, int employeeId) {
		List<Special> list = new ArrayList<Special>();
		Special special;
		try {
			String[] monthsNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 1);
			int totalDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			int firstDate = calendar.get(Calendar.DAY_OF_WEEK);
			int space = firstDate - 1;
			String queryYearAndMonth = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + monthsNumber[calendar.get(Calendar.MONTH)] + "-";
			String queryDate;
			int counter = 1; //日期計數
			PreparedStatement stmt;
			
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
				stmt = con.prepareStatement("select * from [dbo].[SPECIAL] where Spe_Date=? and Emp_ID=?");
				stmt.setString(1, queryYearAndMonth + queryDate);
				stmt.setInt(2, employeeId);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					special = new Special(rs.getString("Spe_Date"), rs.getInt("Emp_ID"), rs.getInt("Counter_ID"), rs.getString("OnTime"), rs.getString("OffTime"), rs.getInt("Changed_By"));
					list.add(special);
				}
				else {
					special = new Special(queryYearAndMonth + queryDate, 0, 0, "", "", 0);
					list.add(special);
				}
				if(rs != null) {
					rs.close();
				}
				counter++;
			}
			
			for(int i=space+totalDate; i<42; i++) {
				special = new Special("", 0, 0, "", "", 0);
				list.add(special);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public  double findOnTime(String date, int employeeId) {
		return 0;
	}
	public  double findOffTime(String date, int employeeId) {
		return 0;
	}
}
