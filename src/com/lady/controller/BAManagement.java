package com.lady.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.EmployeeDAO;
import com.lady.entity.BADTO;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class BAManagement
 */
@WebServlet("/BAManagement")
public class BAManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BAManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		EmployeeDAO employeeDAO = MSSQLDAOFactory.getEmployeeDAO();
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		
		HttpSession session = request.getSession();
		int areaId = (int) session.getAttribute("areaId");
		List<BADTO> list = employeeDAO.findBAFromAreaId(areaId);
		request.setAttribute("BAInfo", list);
		request.getRequestDispatcher("BA_manage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
