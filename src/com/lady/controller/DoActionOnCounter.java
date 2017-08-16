package com.lady.controller;

import java.io.IOException;
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
 * Servlet implementation class DoActionOnCounter
 */
@WebServlet("/DoActionOnCounter")
public class DoActionOnCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoActionOnCounter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		int id = Integer.valueOf(request.getParameter("id"));
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		
		if(command.equals("delete")) {
			counterDAO.deleteCounter(id);
			response.sendRedirect("CounterManagement");
		}
		else if(command.equals("update")) {
			String name = request.getParameter("name");
			String monday = request.getParameter("monday");
			String tuesday = request.getParameter("tuesday");
			String wednesday = request.getParameter("wednesday");
			String thursday = request.getParameter("thursday");
			String friday = request.getParameter("friday");
			String saturday = request.getParameter("saturday");
			String sunday = request.getParameter("sunday");
			
//			counterDAO.deleteCounter(id);
			HttpSession session = request.getSession();
			int areaId = ((Integer) session.getAttribute("areaId")).intValue();
			Counter counter = new Counter(id, name, areaId, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
			counterDAO.updateCounter(id, counter);
			response.sendRedirect("CounterManagement");
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
