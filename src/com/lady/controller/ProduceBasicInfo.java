package com.lady.controller;

import com.google.gson.Gson;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProduceBasicInfo")
public class ProduceBasicInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProduceBasicInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();

        HttpSession session = request.getSession();
        Object defend = session.getAttribute("positionId");
        int positionId = 0;
        if(defend != null) {
            positionId = (int)defend;
            if(positionId == 3) {
                List<Employee> employees = employeeDAO.findPeopleFromPosition2(1);
                Employee[] basicInfo = new Employee[employees.size()];
                basicInfo = employees.toArray(basicInfo);

                String json = new Gson().toJson(basicInfo);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
            else
                response.sendRedirect("index.jsp");
        }
        else
            response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
