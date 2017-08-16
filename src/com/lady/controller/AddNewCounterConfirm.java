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
import com.lady.dao.CounterDAO;
import com.lady.entity.Counter;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class AddNewCounterConfirm
 */
@WebServlet("/AddNewCounterConfirm")
public class AddNewCounterConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewCounterConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		String json = request.getParameter("json");
		List<Counter> counterExcluded = new ArrayList<>();
		Gson gson = new Gson();
		Counter[] counters = gson.fromJson(json, Counter[].class);
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		CounterDAO counterDAO = MSSQLDAOFactory.getCounterDAO();
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		for(int i=0; i<counters.length; i++) {
			int isSuccess = counterDAO.insertCounter(counters[i]);
			if(isSuccess == 0) {
				counterExcluded.add(counters[i]);
			}
		}
		if(counterExcluded.isEmpty()) {
			response.sendRedirect("teamLeader.jsp");
		}
		else {
			request.setAttribute("excludedCounter", counterExcluded);
			request.getRequestDispatcher("teamLeader.jsp").forward(request, response);
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
