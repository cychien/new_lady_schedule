package com.lady.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DB_Init")
public class DB_Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DB_Init() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection.createDatabase("Lady");
		Connection con = DBConnection.createConnection();
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("create table POSITION(Pos_ID int identity(1,1) primary key, Pos_Name varchar(20) not null )");
			stmt.executeUpdate("create table AREA(Area_ID int identity(1,1) primary key, Area_Name varchar(10) not null, Area_Range varchar(10) not null)");
			stmt.executeUpdate("create table COUNTER (Counter_ID int identity(1,1) primary key, "
					+ "Counter_Name varchar(20) not null, "
					+ "Area_ID int not null, "
					+ "Monday varchar(15) not null, "
					+ "Tuesday varchar(15) not null, "
					+ "Wednesday varchar(15) not null, "
					+ "Thursday varchar(15) not null, "
					+ "Friday varchar(15) not null, "
					+ "Saturday varchar(15) not null, "
					+ "Sunday varchar(15) not null, "
					+ "foreign key(Area_ID) references [dbo].[AREA] (Area_ID) on update no action on delete cascade)");
			stmt.executeUpdate("create table EMPLOYEE(Emp_ID int identity(1,1) primary key, "
					+ "Emp_Name varchar(10) not null, "
					+ "Emp_Area int not null, "
					+ "Emp_Account varchar(35) not null, "
					+ "Emp_Password varchar(25) not null, "
					+ "Counter_ID int, "
					+ "Pos_ID int not null, "
					+ "Is_Checked int not null, "
					+ "foreign key(Counter_ID) references [dbo].[COUNTER] (Counter_ID) on update cascade on delete set null, "
					+ "foreign key(Pos_ID) references [dbo].[POSITION] (Pos_ID) on update no action on delete cascade, "
					+ "foreign key(Emp_Area) references [dbo].[AREA] (Area_ID) )");
			stmt.executeUpdate("create table REST (Rest_ID int identity(1,1) primary key, "
					+ "Rest_Date varchar(20) not null, "
					+ "Rest_Type varchar(20) not null, "
					+ "Rest_Description varchar(50), "
					+ "Emp_ID int not null, "
					+ "Is_Changed int not null, "
					+ "Changed_By int, "
					+ "foreign key(Changed_By) references [dbo].[EMPLOYEE] (Emp_ID), "
					+ "foreign key(Emp_ID) references [dbo].[EMPLOYEE] (Emp_ID) on update cascade on delete cascade)");
//			stmt.executeUpdate("create table AUTHORITY(Aut_ID int identity(1,1) primary key, Aut_Name varchar(20) not null )");
//			stmt.executeUpdate("create table GET_ACCESS_TO(Pos_ID int not null, Aut_ID int not null, primary key clustered(Pos_ID, Aut_ID), "
//					+ "foreign key(Pos_ID) references [dbo].[POSITION] (Pos_ID) on update no action on delete cascade, foreign key(Aut_ID) "
//					+ "references [dbo].[AUTHORITY] (Aut_ID) on update no action on delete cascade)");
			stmt.executeUpdate("create table TRANSFER_TO (Tra_Date varchar(20) not null, "
					+ "Emp_ID int not null, "
					+ "Counter_ID int not null, "
					+ "Added_By int , "
					+ "primary key clustered(Tra_Date, Emp_ID, Counter_ID), "
					+ "foreign key(Emp_ID) references [dbo].[EMPLOYEE] (Emp_ID) on update cascade on delete cascade, "
					+ "foreign key(Counter_ID) references [dbo].[COUNTER] (Counter_ID), "
					+ "foreign key(Added_By) references [dbo].[EMPLOYEE] (Emp_ID) )");
			stmt.executeUpdate("create table SPECIAL (Spe_Date varchar(20) not null, "
					+ "Emp_ID int not null, "
					+ "Counter_ID int, "
					+ "OnTime varchar(10) not null, "
					+ "OffTime varchar(10) not null, "
					+ "Changed_By int, "
					+ "primary key clustered(Spe_Date, Emp_ID), "
					+ "foreign key(Emp_ID) references [dbo].[EMPLOYEE] (Emp_ID) on update cascade on delete cascade, "
					+ "foreign key(Counter_ID) references [dbo].[COUNTER] (Counter_ID), "
					+ "foreign key(Changed_By) references [dbo].[EMPLOYEE] (Emp_ID) )");
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
