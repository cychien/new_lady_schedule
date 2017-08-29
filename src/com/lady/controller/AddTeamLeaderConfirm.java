package com.lady.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lady.dao.AreaDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.entity.TeamLeaderDTO;
import com.lady.factory.DAOFactory;

@WebServlet("/AddTeamLeaderConfirm")
public class AddTeamLeaderConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddTeamLeaderConfirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		String json = request.getParameter("json");
		List<TeamLeaderDTO> teamLeaderDTOExcluded = new ArrayList<>();
		Gson gson = new Gson();
		TeamLeaderDTO[] teamLeaderDTOs = gson.fromJson(json, TeamLeaderDTO[].class);
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		EmployeeDAO employeeDAO = MSSQLDAOFactory.getEmployeeDAO();
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		Employee[] employees = new Employee[teamLeaderDTOs.length];
		for(int i=0; i<employees.length; i++) {
			AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
			int areaId = areaDAO.findAreaFromName(teamLeaderDTOs[i].getTeamLeaderArea());
			employees[i] = new Employee(0, 0, teamLeaderDTOs[i].getTeamLeaderName(), areaId, teamLeaderDTOs[i].getTeamLeaderAccount(), teamLeaderDTOs[i].getTeamLeaderPassword(), 2, 1, 0, "X", "X", 0, 0, 0, 0, 0, 0, "X");
			int isSuccess = employeeDAO.insertTeamLeader(employees[i]);
			if(isSuccess == 0) {
				teamLeaderDTOExcluded.add(teamLeaderDTOs[i]);
			}
		}
		if(teamLeaderDTOExcluded.isEmpty()) {
			response.sendRedirect("Admin2");
		}
		else {
			request.setAttribute("excludedPeople", teamLeaderDTOExcluded);
			request.getRequestDispatcher("Admin2").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
