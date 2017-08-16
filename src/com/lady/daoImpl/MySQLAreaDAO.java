package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lady.dao.AreaDAO;
import com.lady.entity.Area;
import com.lady.util.DBConnection2;

public class MySQLAreaDAO implements AreaDAO {
	private Area area;
	
	public List<Area> readArea() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Area> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from AREA");
			rs = stmt.executeQuery();
			while (rs.next()) {
				area = new Area(rs.getInt("Area_ID"), rs.getString("Area_Name"), rs.getString("Area_Range"));
				list.add(area);
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
			return  list;
		}
	}

	
	public int insertArea(Area area) {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		int result = 1;
		String areaName = area.getAreaName();
		String areaRange = area.getAreaRange();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Area_Name from AREA");
			rs = stmt.executeQuery();
			while(rs.next()) {
				String areaNameDB = rs.getString("Area_Name"); 
				if(areaName.equals(areaNameDB)) {
					result = 0;
				}
			}

			if(result != 0) {
				stmt2 = con.prepareStatement("insert into AREA (Area_Name, Area_Range) values (?,?)");
				stmt2.setString(1, areaName);
				stmt2.setString(2, areaRange);
				stmt2.execute();
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
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
			return result;
		}
	}

	
	public void deleteArea(int areaId) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from AREA where Area_ID=?");
			stmt.setInt(1, areaId);
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


	public int findAreaFromName(String areaName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int areaId = 0;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Area_ID from AREA where Area_Name=?");
			stmt.setString(1, areaName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				areaId = rs.getInt("Area_ID");
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
			return areaId;
		}
	}
	
	public String findAreaFromId(int areaId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String areaName = "";
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select Area_Name from AREA where Area_ID=?");
			stmt.setInt(1, areaId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				areaName = rs.getString("Area_Name");
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
			return areaName;
		}
	}
	
	public List<Area> findAreaFromEmployeeName(String employeeName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Area> list = new ArrayList<>();
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("select * from AREA,EMPLOYEE where AREA.Area_ID=EMPLOYEE.Emp_Area and EMPLOYEE.Emp_Name=?");
			stmt.setString(1, employeeName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				area = new Area(rs.getInt("Area_ID"), rs.getString("Area_Name"), rs.getString("Area_Range"));
				list.add(area);
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
	
	public void updateArea(int areaId, Area area) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update AREA set Area_Name=?, Area_Range=? WHERE Area_ID=?");
			stmt.setString(1, area.getAreaName());
			stmt.setString(2, area.getAreaRange());
			stmt.setInt(3, area.getAreaId());
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
