package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		Employee employee = new Employee();
		employee.setEmployeeAccount(account);
		employee.setEmployeePassword(password);
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		
		String loginResult = employeeDAO.authenticateUser(employee);
		
		if(loginResult.equals("沒有這個帳戶")) {
			request.setAttribute("message", loginResult);
			request.getRequestDispatcher("").forward(request, response);
		} 
		else if(loginResult.equals("此帳戶尚未取得開通")) {
			request.setAttribute("message", loginResult);
			request.getRequestDispatcher("").forward(request, response);
		}
		else {
			Employee employee2 = employeeDAO.selectEmployee(Integer.valueOf(loginResult));
			if(employee2.getPositionID() == 1) {
				int employeeId = Integer.parseInt(loginResult);
				HttpSession session = request.getSession();
				session.setAttribute("employeeId", employeeId);
				response.sendRedirect("currentMonth.jsp");
			}
			else {
				request.setAttribute("message", "沒有這個帳戶");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
