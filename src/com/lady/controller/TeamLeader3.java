package com.lady.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Counter;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class TeamLeader3
 */
@WebServlet("/TeamLeader3")
public class TeamLeader3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamLeader3() {
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
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();

		HttpSession session = request.getSession();
		int areaId = (int) session.getAttribute("areaId");
		
		List<String> nextMonth = new ArrayList<>();
		String[] weekdays ={"X", "日","一","二","三","四","五","六"};
		
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int dates = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weekdayIndex = calendar.get(Calendar.DAY_OF_WEEK);
		for(int i=weekdayIndex; i<weekdayIndex+dates; i++) {
			int j = i % 7;
			if(j == 0)
				j = 7;
			nextMonth.add(weekdays[j]);
		}
		
		List<Counter> counterList = new ArrayList<>();
		counterList = counterDAO.findCounterFromAreaId(areaId);

		double totalCompanyHour = employeeDAO.calTotalCompanyHour();

		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("counterList", counterList);
		request.setAttribute("totalCompanyHour", totalCompanyHour);
		request.getRequestDispatcher("teamLeader3.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
