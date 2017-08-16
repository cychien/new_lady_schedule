package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.AreaDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

@WebServlet("/LoginAdminController")
public class LoginAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginAdminController() {
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
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		
		String loginResult = employeeDAO.authenticateUser(employee);
		
		if(loginResult.equals("沒有這個帳戶") | loginResult.equals("此帳戶尚未取得開通")) {
			request.setAttribute("message", "沒有這個帳戶");
			request.getRequestDispatcher("admin_login.jsp").forward(request, response);
		} 
		else {
			int position = employeeDAO.findPositionFromPeopleId(Integer.valueOf(loginResult));
			if(position == 2) {
				int employeeId = Integer.parseInt(loginResult);
				Employee employee2 = employeeDAO.selectEmployee(employeeId);
				String areaName = areaDAO.findAreaFromId(employee2.getEmployeeArea());
				HttpSession session = request.getSession();
				session.setAttribute("employeeId", employeeId);
				session.setAttribute("areaId", employee2.getEmployeeArea());
				session.setAttribute("areaName", areaName);
				session.setAttribute("positionId", employee2.getPositionID());
				response.sendRedirect("TeamLeader");
			}
			else if(position == 3){
				int employeeId = Integer.parseInt(loginResult);
				Employee employee3 = employeeDAO.selectEmployee(employeeId);
				String areaName = areaDAO.findAreaFromId(employee3.getEmployeeArea());
				HttpSession session = request.getSession();
				session.setAttribute("employeeId", employeeId);
				session.setAttribute("areaId", employee3.getEmployeeArea());
				session.setAttribute("areaName", areaName);
				session.setAttribute("positionId", employee3.getPositionID());
				response.sendRedirect("CheckPeople");
			}
			else if(position == 1) {
				request.setAttribute("message", "沒有這個帳戶");
				request.getRequestDispatcher("admin_login.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
