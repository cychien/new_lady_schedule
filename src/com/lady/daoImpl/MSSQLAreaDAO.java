package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lady.dao.AreaDAO;
import com.lady.entity.Area;
import com.lady.util.DBConnection;

public class MSSQLAreaDAO implements AreaDAO {

	Connection con = DBConnection.createConnection();
	private Area area;
	
	public List<Area> readArea() {
		List<Area> list = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[AREA]");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				area = new Area(rs.getInt("Area_ID"), rs.getString("Area_Name"), rs.getString("Area_Range"));
				list.add(area);
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

	
	public int insertArea(Area area) {
		String areaName = area.getAreaName();
		String areaRange = area.getAreaRange();
		try {
			PreparedStatement stmt = con.prepareStatement("select Area_Name from [dbo].[AREA]");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String areaNameDB = rs.getString("Area_Name"); 
				if(areaName.equals(areaNameDB)) {
					return 0;
				}
			}
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			
			PreparedStatement stmt2 = con.prepareStatement("insert into [dbo].[AREA] (Area_Name, Area_Range) values (?,?)");
			stmt2.setString(1, areaName);
			stmt2.setString(2, areaRange);
			stmt2.execute();
			if(stmt2 != null) {
				stmt2.close();
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	
	public void deleteArea(int areaId) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from [dbo].[AREA] where Area_ID=?");
			stmt.setInt(1, areaId);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}


	public int findAreaFromName(String areaName) {
		try {
			int areaId = 0;
			PreparedStatement stmt = con.prepareStatement("select Area_ID from [dbo].[AREA] where Area_Name=?");
			stmt.setString(1, areaName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				areaId = rs.getInt("Area_ID");
			}
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			return areaId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String findAreaFromId(int areaId) {
		try {
			String areaName = "";
			PreparedStatement stmt = con.prepareStatement("select Area_Name from [dbo].[AREA] where Area_ID=?");
			stmt.setInt(1, areaId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				areaName = rs.getString("Area_Name");
			}
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			return areaName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public List<Area> findAreaFromEmployeeName(String employeeName) {
		List<Area> list = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[AREA],[dbo].[EMPLOYEE] where [dbo].[AREA].[Area_ID]=[dbo].[EMPLOYEE].[Emp_Area] and [dbo].[EMPLOYEE].[Emp_Name]=?");
			stmt.setString(1, employeeName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				area = new Area(rs.getInt("Area_ID"), rs.getString("Area_Name"), rs.getString("Area_Range"));
				list.add(area);
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
	
	public void updateArea(int areaId, Area area) {
		try {
			PreparedStatement stmt = con.prepareStatement("update [dbo].[AREA] set Area_Name=?, Area_Range=? WHERE Area_ID=?");
			stmt.setString(1, area.getAreaName());
			stmt.setString(2, area.getAreaRange());
			stmt.setInt(3, area.getAreaId());
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
