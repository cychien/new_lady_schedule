package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lady.dao.EmployeeDAO;
import com.lady.factory.DAOFactory;

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
