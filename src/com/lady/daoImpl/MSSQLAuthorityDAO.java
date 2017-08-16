package com.lady.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lady.dao.AuthorityDAO;
import com.lady.entity.Authority;
import com.lady.util.DBConnection;

public class MSSQLAuthorityDAO implements AuthorityDAO{
	Connection con = DBConnection.createConnection();
	private Authority authority;
	
	public List<Authority> readCounter() {
		List<Authority> list = new ArrayList<Authority>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from [dbo].[AUTHORITY]");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				authority = new Authority(rs.getInt("Aut_ID"), rs.getString("Aut_Name"));
				list.add(authority);
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

}
