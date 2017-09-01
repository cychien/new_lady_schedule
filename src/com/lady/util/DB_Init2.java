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

@WebServlet("/DB_Init2")
public class DB_Init2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DB_Init2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection2.createDatabase("lady");
		Connection con = DBConnection2.createConnection();
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("create table POSITION (Pos_ID int auto_increment, Pos_Name varchar(20) not null, primary key (Pos_ID) )");
			stmt.executeUpdate("create table AREA (Area_ID int auto_increment, Area_Name varchar(10) not null, Area_Range varchar(10) not null, primary key (Area_ID))");
			stmt.executeUpdate("create table COUNTER (Counter_ID int auto_increment, "
					+ "Counter_Name varchar(20) not null, "
					+ "Area_ID int not null, "
					+ "Monday varchar(15) not null, "
					+ "Tuesday varchar(15) not null, "
					+ "Wednesday varchar(15) not null, "
					+ "Thursday varchar(15) not null, "
					+ "Friday varchar(15) not null, "
					+ "Saturday varchar(15) not null, "
					+ "Sunday varchar(15) not null, "
					+ "primary key (Counter_ID), "
					+ "foreign key (Area_ID) references AREA (Area_ID) on update no action on delete cascade)");
			stmt.executeUpdate("create table EMPLOYEE (Emp_ID int auto_increment, "
					+ "Emp_Name varchar(10) not null, "
					+ "Emp_Area int not null, "
					+ "Emp_Account varchar(35) not null, "
					+ "Emp_Password varchar(25) not null, "
					+ "Counter_ID int, "
					+ "Pos_ID int not null, "
					+ "Is_Checked int not null, "
//New Version
					+ "Base int, "
					+ "Pay_Method varchar(20) not null, "
					+ "Start_Date varchar(20) not null, "
					+ "Performance_Bonus int, "
					+ "Education_Bonus int, "
					+ "Owner_Bonus int, "
					+ "Allowance int, "
					+ "Insurance_Minus int, "
					+ "Insurance int, "
					+ "Company varchar(20) not null, "

					+ "primary key (Emp_ID), "
					+ "foreign key (Counter_ID) references COUNTER (Counter_ID) on update cascade on delete set null, "
					+ "foreign key (Pos_ID) references POSITION (Pos_ID) on update no action on delete cascade, "
					+ "foreign key (Emp_Area) references AREA (Area_ID) )");
			stmt.executeUpdate("create table REST (Rest_ID int auto_increment, "
					+ "Rest_Date varchar(20) not null, "
					+ "Rest_Type varchar(20) not null, "
					+ "Rest_Description varchar(50), "
					+ "Emp_ID int not null, "
					+ "Is_Changed int not null, "
					+ "Changed_By int, "
					+ "primary key (Rest_ID), "
					+ "foreign key (Changed_By) references EMPLOYEE (Emp_ID), "
					+ "foreign key (Emp_ID) references EMPLOYEE (Emp_ID) on update cascade on delete cascade)");
//			stmt.executeUpdate("create table AUTHORITY(Aut_ID int identity(1,1) primary key, Aut_Name varchar(20) not null )");
//			stmt.executeUpdate("create table GET_ACCESS_TO(Pos_ID int not null, Aut_ID int not null, primary key clustered(Pos_ID, Aut_ID), "
//					+ "foreign key(Pos_ID) references [dbo].[POSITION] (Pos_ID) on update no action on delete cascade, foreign key(Aut_ID) "
//					+ "references [dbo].[AUTHORITY] (Aut_ID) on update no action on delete cascade)");
			stmt.executeUpdate("create table TRANSFER_TO (Tra_Date varchar(20) not null, "
					+ "Emp_ID int not null, "
					+ "Counter_ID int not null, "
					+ "Added_By int , "
					+ "primary key (Tra_Date, Emp_ID, Counter_ID), "
					+ "foreign key (Emp_ID) references EMPLOYEE (Emp_ID) on update cascade on delete cascade, "
					+ "foreign key (Counter_ID) references COUNTER (Counter_ID), "
					+ "foreign key (Added_By) references EMPLOYEE (Emp_ID) )");
			stmt.executeUpdate("create table SPECIAL (Spe_Date varchar(20) not null, "
					+ "Emp_ID int not null, "
					+ "Counter_ID int, "
					+ "OnTime varchar(10) not null, "
					+ "OffTime varchar(10) not null, "
					+ "Changed_By int, "
					+ "primary key (Spe_Date, Emp_ID), "
					+ "foreign key (Emp_ID) references EMPLOYEE (Emp_ID) on update cascade on delete cascade, "
					+ "foreign key (Counter_ID) references COUNTER (Counter_ID), "
					+ "foreign key (Changed_By) references EMPLOYEE (Emp_ID) )");
			stmt.executeUpdate("CREATE TABLE BONUS (Emp_ID int not null, "
					+ "Emp_Name varchar(10) not null, "
					+ "Month int not null, "
					+ "Year int not null, "
					+ "Money int not null, "
					+ "Modify int not null, "
					+ "PRIMARY KEY (Emp_ID, Month, Year), "
					+ "FOREIGN KEY (Emp_ID) REFERENCES EMPLOYEE (Emp_ID) on update cascade on delete cascade)");
			stmt.executeUpdate("CREATE TABLE CHANGE_DATA (Emp_ID int not null, "
					+ "Month int not null, "
					+ "Year int not null, "
					+ "Business_Bonus int, "
					+ "Target_Bonus int, "
					+ "Management_Bonus int, "
					+ "Year_Bonus int, "
					+ "Other_Bonus int, "
					+ "Supplement_Minus int, "
					+ "Charge_Minus int, "
					+ "Violation_Minus int, "
					+ "Buy_Minus int, "
					+ "Phone_Minus int, "
					+ "Check_Minus int, "
					+ "Borrow_Minus int, "
					+ "Court_Minus int, "
					+ "Other_Minus int, "
					+ "Sales_Performance int"
					+ "PRIMARY KEY (Emp_ID, Month, Year))");
			//有很多...
			stmt.executeUpdate("CREATE TABLE PaySummaryInfo (Emp_ID int not null, "
					+ "Emp_Name varchar(10) not null, "
					+ "Start_Time VARCHAR(20) not null, "
					+ "Pay_Method VARCHAR(30) not null, "
					+ "Year int not null, "
					+ "Month int not null, "
					+ "Base int, "
					+ "Overtime int, "
					+ "Performance_Bonus int, "
					+ "Education_Bonus int, "
					+ "Owner_Bonus int, "
					+ "Allowance int, "
					+ "insuranceMinus int, "
					+ "Insurance int, "
					+ "Bonus int, "
					+ "Business_Bonus int, "
					+ "Target_Bonus int, "
					+ "Management_Bonus int, "
					+ "Year_Bonus int, "
					+ "Other_Bonus int, "
					+ "Supplement_Minus int, "
					+ "Charge_Minus int, "
					+ "Violation_Minus int, "
					+ "Buy_Minus int, "
					+ "Phone_Minus int, "
					+ "Check_Minus int, "
					+ "Borrow_Minus int, "
					+ "Court_Minus int, "
					+ "Other_Minus int, "
					+ "Salary int, "
					+ "PRIMARY KEY (Emp_ID, Year, Month))");
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
