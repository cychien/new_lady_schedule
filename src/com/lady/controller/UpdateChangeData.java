package com.lady.controller;

import com.lady.dao.ChangeDataDAO;
import com.lady.factory.DAOFactory;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateChangeData")
public class UpdateChangeData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateChangeData() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        ChangeDataDAO changeDataDAO = MySQLDAOFactory.getChangeDataDAO();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        request.setCharacterEncoding("utf-8");
        String pk = request.getParameter("pk");
        String value = request.getParameter("value");
        String[] pkArr = pk.split("-");
        int employeeId = Integer.valueOf(pkArr[0]);
        String column = pkArr[1];
        changeDataDAO.modifyChangeData(employeeId, column, value, year, month+1);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}