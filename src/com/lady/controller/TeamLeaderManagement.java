package com.lady.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lady.dao.EmployeeDAO;
import com.lady.entity.TeamLeaderDTO;
import com.lady.factory.DAOFactory;

@WebServlet("/TeamLeaderManagement")
public class TeamLeaderManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeamLeaderManagement() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		List<TeamLeaderDTO> list = employeeDAO.findPeopleFromPosition(2);
		request.setAttribute("teamLeaderInfo", list);
		request.getRequestDispatcher("admin2_manage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
