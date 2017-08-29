package com.lady.controller;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lady.dao.EmployeeDAO;
import com.lady.factory.DAOFactory;
import com.lady.util.ConvertToLegalNumber;

@WebServlet("/CheckPeopleConfirm")
public class CheckPeopleConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckPeopleConfirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		
		String[] checkboxList =  request.getParameterValues("checkbox");
		if(command.equals("confirm")) {
			System.out.println("confirm");
			for(int i=0; i<checkboxList.length; i++) {
				if(checkboxList[i] != null) {
					employeeDAO.updateEmployeeIsChecked(Integer.parseInt(checkboxList[i]), 1);
					Calendar calendar = Calendar.getInstance();
					String[] monthNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					String date = String.valueOf(day);
					if(day < 10)
						date = "0" + date;
					String startDate = calendar.get(Calendar.YEAR) + "-" + monthNumber[calendar.get(Calendar.MONTH)] + "-" + date;
					employeeDAO.updateEmployeeStartDate(Integer.parseInt(checkboxList[i]), startDate);
				}
			}
		}
		else if(command.equals("delete")) {
			System.out.println("delete");
			for(int i=0; i<checkboxList.length; i++) {
				if(checkboxList[i] != null) {
					employeeDAO.deleteEmployee(Integer.parseInt(checkboxList[i]));
				}
			}
		}
		response.sendRedirect("CheckPeople");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
