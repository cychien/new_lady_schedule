package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lady.dao.PositionDAO;
import com.lady.entity.Position;
import com.lady.util.DBConnection2;

public class MySQLPositionDAO implements PositionDAO{
	
	public void insertEmployee(Position position) {
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("insert into POSITION (Pos_Name) values (?)");
			stmt.setString(1, position.getPositionName());
			stmt.execute();
		}catch(SQLException e) {
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

	public void deleteEmployee(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("delete from POSITION where Pos_ID=?");
			stmt.setInt(1, id);
			stmt.execute();
		}catch(SQLException e) {
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

	public void updateEmployee(int id, Position position) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnection2.createConnection();
			stmt = con.prepareStatement("update POSITION set Pos_Name = ? WHERE Pos_ID=?");
			stmt.setInt(1, id);
			stmt.setString(2, position.getPositionName());
			stmt.execute();
		}catch(SQLException e) {
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
