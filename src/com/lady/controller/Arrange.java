package com.lady.controller;

import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Arrange")
public class Arrange extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Arrange() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
//        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
//        HttpSession session = request.getSession();
//        int employeeId = (int)session.getAttribute("employeeId");
//        Employee employee = employeeDAO.selectEmployee(employeeId);
//        String user = employee.getEmployeeName();
//        request.setAttribute("user", user);
        request.getRequestDispatcher("arrange.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
