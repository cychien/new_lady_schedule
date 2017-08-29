package com.lady.controller;

import com.lady.dao.EmployeeDAO;
import com.lady.factory.DAOFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BasicInfo")
public class BasicInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BasicInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String employeeId = request.getParameter("employeeId");
        String base = request.getParameter("base");
        String payMethod = request.getParameter("payMethod");
        String performanceBonus = request.getParameter("performanceBonus");
        String educationBonus = request.getParameter("educationBonus");
        String ownerBonus = request.getParameter("ownerBonus");
        String allowance = request.getParameter("allowance");
        String insuranceMinus = request.getParameter("insuranceMinus");
        String insurance = request.getParameter("insurance");
        String company = request.getParameter("company");

        DAOFactory MySQKDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        EmployeeDAO employeeDAO = MySQKDAOFactory.getEmployeeDAO();
        employeeDAO.updateBasicInfo(Integer.valueOf(employeeId), Integer.valueOf(base), payMethod, Integer.valueOf(performanceBonus), Integer.valueOf(educationBonus), Integer.valueOf(ownerBonus), Integer.valueOf(allowance), Integer.valueOf(insuranceMinus), Integer.valueOf(insurance), company);

        response.sendRedirect("CheckPeople");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
