package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lady.dao.AreaDAO;
//import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String area = request.getParameter("area");
//		String counter = request.getParameter("counter");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
//		CounterDAO counterDAO = MSSQLDAOFactory.getCounterDAO();
		
//		int counterId = counterDAO.findCounterId(counter);
//		System.out.println(counterId);
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		int areaId = areaDAO.findAreaFromName(area);
		
		Employee employee = new Employee(0, 0,username, areaId, account, password, 1, 0);
		
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		int newEmployeeId = employeeDAO.insertEmployee(employee);
		
		if(newEmployeeId != 0) {
			response.sendRedirect("index.jsp");
		}
		else {
			response.sendRedirect("register.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
