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
import com.lady.util.DBConnection2;

public class MySQLCounterDAO implements CounterDAO{
	private Counter counter;
	
	public List<Counter> readCounter() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Counter> list = new ArrayList<Counter>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from COUNTER");
			rs = stmt.executeQuery();
			while (rs.next()) {
				counter = new Counter(rs.getInt("Counter_ID"), rs.getString("Counter_Name"), rs.getInt("Area_ID"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday"), rs.getString("Saturday"), rs.getString("Sunday"));
				list.add(counter);
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
	
	public int insertCounter(Counter counter) {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int counterId = 1;
		try {
			con = DBConnection2.createConnection();
			String counterName = counter.getCounterName();
			
			stmt3 = con.prepareStatement("select Counter_Name from COUNTER");
			rs = stmt3.executeQuery();
			
			while(rs.next()) {
				String counterNameDB = rs.getString("Counter_Name"); 
				if(counterName.equals(counterNameDB)) {
					counterId = 0;
				}
			}

			if(counterId != 0) {
				stmt = con.prepareStatement("insert into COUNTER (Counter_Name, Area_ID, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday) values (?,?,?,?,?,?,?,?,?)");
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

				stmt2 = con.prepareStatement("select Counter_ID from COUNTER order by Counter_ID desc");
				rs2 = stmt2.executeQuery();
				if(rs2.next()) {
					counterId = rs2.getInt("Counter_ID");
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
			return counterId;
		}
	}
	
	public void deleteCounter(int counterId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from COUNTER where Counter_ID=?");
			stmt.setInt(1, counterId);
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
	
	public void updateCounter(int counterId, Counter counter) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update COUNTER set Counter_Name=?,Monday=?,Tuesday=?,Wednesday=?,Thursday=?,Friday=?,Saturday=?,Sunday=? WHERE Counter_ID=?");
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
	
	public Counter selectCounter(int counterId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		counter = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from COUNTER where Counter_ID=?");
			stmt.setInt(1, counterId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counter = new Counter(rs.getInt("Counter_ID"), rs.getString("Counter_Name"), rs.getInt("Area_ID"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday"), rs.getString("Saturday"), rs.getString("Sunday")); 
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
			return counter;
		}
	}
	
	public int findCounterId(String counterName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int counterId = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from COUNTER where Counter_Name=?");
			stmt.setString(1, counterName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counterId = rs.getInt("Counter_ID");
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
			return counterId;
		}
	}
	
	public List<Counter> findCounterFromAreaId(int areaId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Counter> list = new ArrayList<Counter>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from COUNTER where Area_ID=?");
			stmt.setInt(1, areaId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				counter = new Counter(rs.getInt("Counter_ID"), rs.getString("Counter_Name"), rs.getInt("Area_ID"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday"), rs.getString("Saturday"), rs.getString("Sunday"));
				list.add(counter);
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
	
	public String[] getWorkTime(int counterId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String[] workTime = new String[7];
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday from COUNTER where Counter_ID=?");
			stmt.setInt(1, counterId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				workTime[0] = rs.getString("Monday");
				workTime[1] = rs.getString("Tuesday");
				workTime[2] = rs.getString("Wednesday");
				workTime[3] = rs.getString("Thursday");
				workTime[4] = rs.getString("Friday");
				workTime[5] = rs.getString("Saturday");
				workTime[6] = rs.getString("Sunday");
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
			return workTime;
		}
	}
	
	public String findCounterName(int counterId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String counterName = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from COUNTER where Counter_ID=?");
			stmt.setInt(1, counterId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counterName = rs.getString("Counter_Name");
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
			return counterName;
		}
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
		String[] onTimeArr = new String [7];
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
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from COUNTER where Area_ID=? and Counter_ID=?");
			stmt.setInt(1, areaId);
			stmt.setInt(2, counterId);
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
}
