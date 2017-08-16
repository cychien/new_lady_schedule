package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lady.dao.AreaDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class DoActionOnTeamLeader
 */
@WebServlet("/DoActionOnTeamLeader")
public class DoActionOnTeamLeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoActionOnTeamLeader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		int id = Integer.valueOf(request.getParameter("id"));
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		
		if(command.equals("delete")) {
			employeeDAO.deleteEmployee(id);
			response.sendRedirect("TeamLeaderManagement");
		}
		else if(command.equals("update")) {
			String name = request.getParameter("name");
			String area = request.getParameter("area");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			
			AreaDAO  areaDAO = MySQLDAOFactory.getAreaDAO();
			int areaId = areaDAO.findAreaFromName(area);
			
//			employeeDAO.deleteEmployee(id);
			Employee employee = new Employee(id, 0, name, areaId, account, password, 2, 1);
			employeeDAO.updateEmployee(id,employee);
			response.sendRedirect("TeamLeaderManagement");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
