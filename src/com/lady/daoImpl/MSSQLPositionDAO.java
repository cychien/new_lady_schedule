package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lady.dao.PositionDAO;
import com.lady.entity.Position;
import com.lady.util.DBConnection;

public class MSSQLPositionDAO implements PositionDAO{
	Connection con = DBConnection.createConnection();
	
	public void insertEmployee(Position position) {
		try{
			PreparedStatement stmt = con.prepareStatement("insert into [dbo].[POSITION] (Pos_Name) values (?)");
			stmt.setString(1, position.getPositionName());
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int id) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from [dbo].[POSITION] where Pos_ID=?");
			stmt.setInt(1, id);
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(int id, Position position) {
		try {
			PreparedStatement stmt = con.prepareStatement("update [dbo].[POSITION] set Pos_Name = ? WHERE Pos_ID=?");
			stmt.setInt(1, id);
			stmt.setString(2, position.getPositionName());
			stmt.execute();
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
