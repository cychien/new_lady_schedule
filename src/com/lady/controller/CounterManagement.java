package com.lady.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.CounterDAO;
import com.lady.entity.Counter;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class CounterManagement
 */
@WebServlet("/CounterManagement")
public class CounterManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounterManagement() {
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
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		HttpSession session = request.getSession();
		int areaId = (int) session.getAttribute("areaId");
		List<Counter> list = counterDAO.findCounterFromAreaId(areaId);
		request.setAttribute("counterInfo", list);
		request.getRequestDispatcher("teamLeader_manage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
