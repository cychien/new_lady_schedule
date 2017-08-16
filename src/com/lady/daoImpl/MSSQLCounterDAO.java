package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lady.dao.CounterDAO;
import com.lady.entity.Counter;
import com.lady.util.ConvertToLegalNumber;
import com.lady.util.DBConnection;

public class MSSQLCounterDAO implements CounterDAO{
	Connection con = DBConnection.createConnection();
	private Counter counter;
	
	public List<Counter> readCounter() {
		List<Counter> list = new ArrayList<Counter>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[COUNTER]");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				counter = new Counter(rs.getInt("Counter_ID"), rs.getString("Counter_Name"), rs.getInt("Area_ID"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday"), rs.getString("Saturday"), rs.getString("Sunday"));
				list.add(counter);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertCounter(Counter counter) {
		try {
			String counterName = counter.getCounterName();
			
			PreparedStatement stmt3 = con.prepareStatement("select Counter_Name from [dbo].[COUNTER]");
			ResultSet rs = stmt3.executeQuery();
			
			while(rs.next()) {
				String counterNameDB = rs.getString("Counter_Name"); 
				if(counterName.equals(counterNameDB)) {
					return 0;
				}
			}
			if (rs != null) {
				rs.close();
			}
			if(stmt3 != null) {
				stmt3.close();
			}
			
			int counterId = 0;
			
			PreparedStatement stmt = con.prepareStatement("insert into [dbo].[COUNTER] (Counter_Name, Area_ID, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday) values (?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, counter.getCounterName());
			stmt.setInt(2, counter.getAreaId());
			stmt.setString(3, counter.getMonday());
			stmt.setString(4, counter.getTuesday());
			stmt.setString(5, counter.getWednesday());
			stmt.setString(6, counter.getThursday());
			stmt.setString(7, counter.getFriday());
			stmt.setString(8, counter.getSaturday());
			stmt.setString(9, counter.getSunday());
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
			PreparedStatement stmt2 = con.prepareStatement("select Counter_ID from [dbo].[COUNTER] order by Counter_ID desc");
			ResultSet rs2 = stmt2.executeQuery();
			if(rs2.next()) {
				counterId = rs2.getInt("Counter_ID");
			}
			if (rs2 != null) {
				rs.close();
			}
			if(stmt2 != null) {
				stmt2.close();
			}
			return counterId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deleteCounter(int counterId) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from [dbo].[COUNTER] where Counter_ID=?");
			stmt.setInt(1, counterId);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCounter(int counterId, Counter counter) {
		try {
			PreparedStatement stmt = con.prepareStatement("update [dbo].[COUNTER] set Counter_Name=?,Monday=?,Tuesday=?,Wednesday=?,Thursday=?,Friday=?,Saturday=?,Sunday=? WHERE Counter_ID=?");
			stmt.setString(1, counter.getCounterName());
			stmt.setString(2, counter.getMonday());
			stmt.setString(3, counter.getTuesday());
			stmt.setString(4, counter.getWednesday());
			stmt.setString(5, counter.getThursday());
			stmt.setString(6, counter.getFriday());
			stmt.setString(7, counter.getSaturday());
			stmt.setString(8, counter.getSunday());
			stmt.setInt(9, counterId);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Counter selectCounter(int counterId) {
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[COUNTER] where Counter_ID=?");
			stmt.setInt(1, counterId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				counter = new Counter(rs.getInt("Counter_ID"), rs.getString("Counter_Name"), rs.getInt("Area_ID"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday"), rs.getString("Saturday"), rs.getString("Sunday")); 
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			return counter;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int findCounterId(String counterName) {
		try {
			int counterId;
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[COUNTER] where Counter_Name=?");
			stmt.setString(1, counterName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				counterId = rs.getInt("Counter_ID");
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				return counterId;
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
		return 0;
	}
	
	public List<Counter> findCounterFromAreaId(int areaId) {
		List<Counter> list = new ArrayList<Counter>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[COUNTER] where Area_ID=?");
			stmt.setInt(1, areaId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				counter = new Counter(rs.getInt("Counter_ID"), rs.getString("Counter_Name"), rs.getInt("Area_ID"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday"), rs.getString("Saturday"), rs.getString("Sunday"));
				list.add(counter);
			}
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String[] getWorkTime(int counterId) {
		try {
			String[] workTime = new String[7];
			PreparedStatement stmt = con.prepareStatement("select Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday from [dbo].[COUNTER] where Counter_ID=?");
			stmt.setInt(1, counterId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				workTime[0] = rs.getString("Monday");
				workTime[1] = rs.getString("Tuesday");
				workTime[2] = rs.getString("Wednesday");
				workTime[3] = rs.getString("Thursday");
				workTime[4] = rs.getString("Friday");
				workTime[5] = rs.getString("Saturday");
				workTime[6] = rs.getString("Sunday");
			}
			return workTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String findCounterName(int counterId) {
		try {
			String counterName;
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[COUNTER] where Counter_ID=?");
			stmt.setInt(1, counterId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				counterName = rs.getString("Counter_Name");
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				return counterName;
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
		return null;
	}
	
	public String findOnTimeFromDateAndId(String date, int counterId) {
		int[] weekdays ={-1, 6, 0, 1, 2, 3, 4, 5};
		
		String[] arr = date.split("-");
		int year = Integer.valueOf(arr[0]);
		int month = ConvertToLegalNumber.convertTo(arr[1]);
		int day = ConvertToLegalNumber.convertTo(arr[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day);
		int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
		
		String[] workTimeArr = getWorkTime(counterId);
		String[] onTimeArr = new String[7];
		for(int i=0; i<7; i++) {
			String[] arr2 = workTimeArr[i].split("-");
			onTimeArr[i] = arr2[0];
		}
		String onTime = onTimeArr[weekdays[weekdayIndex]];
		return onTime;
	}
	
	public String findOffTimeFromDateAndId(String date, int counterId) {
		int[] weekdays ={-1, 6, 0, 1, 2, 3, 4, 5};
		
		String[] arr = date.split("-");
		int year = Integer.valueOf(arr[0]);
		int month = ConvertToLegalNumber.convertTo(arr[1]);
		int day = ConvertToLegalNumber.convertTo(arr[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day);
		int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
		
		String[] workTimeArr = getWorkTime(counterId);
		String[] offTimeArr = new String[7];
		for(int i=0; i<7; i++) {
			String[] arr2 = workTimeArr[i].split("-");
			offTimeArr[i] = arr2[1];
		}
		String offTime = offTimeArr[weekdays[weekdayIndex]];
		return offTime;
	}
	
	public int isValid(int areaId, int counterId) {
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[COUNTER] where Area_ID=? and Counter_ID=?");
			stmt.setInt(1, areaId);
			stmt.setInt(2, counterId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
