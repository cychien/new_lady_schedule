package com.lady.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lady.dao.EmployeeDAO;
import com.lady.entity.PeopleInfoDTO;
import com.lady.factory.DAOFactory;

@WebServlet("/CheckPeople")
public class CheckPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckPeople() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<PeopleInfoDTO> uncheckedPeople = new ArrayList<>();
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		uncheckedPeople = employeeDAO.findPepleFromUnchecked(0);
		request.setAttribute("uncheckedPeople", uncheckedPeople);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}