package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.AreaDAO;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class ChangeAreaSession
 */
@WebServlet("/ChangeArea")
public class ChangeArea extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeArea() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int areaId = Integer.valueOf(request.getParameter("areaId"));
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
        String areaName = areaDAO.findAreaFromId(areaId);
        HttpSession session = request.getSession();
        session.setAttribute("selectedAreaId",areaId);
        session.setAttribute("selectedAreaName", areaName);
        request.getRequestDispatcher("SalesPerformance").forward(request, response);;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
