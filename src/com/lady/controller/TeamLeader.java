package com.lady.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.AreaDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Area;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class TeamLeader
 */
@WebServlet("/TeamLeader")
public class TeamLeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamLeader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		HttpSession session = request.getSession();
		int employeeId = (int) session.getAttribute("employeeId");
		Employee employee = employeeDAO.selectEmployee(employeeId);
		String employeeName = employee.getEmployeeName();
		List<Area> list = new ArrayList<>();
		list = areaDAO.findAreaFromEmployeeName(employeeName);
		request.setAttribute("area", list);

		request.setAttribute("user", employeeName);

		request.getRequestDispatcher("teamLeader_menu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
