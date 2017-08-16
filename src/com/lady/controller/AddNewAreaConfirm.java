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
import com.lady.dao.AreaDAO;
import com.lady.entity.Area;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class AddNewAreaConfirm
 */
@WebServlet("/AddNewAreaConfirm")
public class AddNewAreaConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewAreaConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		String json = request.getParameter("json");
		List<Area> areaExcluded = new ArrayList<>();
		Gson gson = new Gson();
		Area[] areas = gson.fromJson(json, Area[].class);
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		AreaDAO areaDAO = MSSQLDAOFactory.getAreaDAO();
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
		for(int i=0; i<areas.length; i++) {
			int isSuccess = areaDAO.insertArea(areas[i]);
			if(isSuccess == 0) {
				areaExcluded.add(areas[i]);
			}
		}
		if(areaExcluded.isEmpty()) {
			response.sendRedirect("admin3.jsp");
		}
		else {
			request.setAttribute("excludedArea", areaExcluded);
			request.getRequestDispatcher("admin3.jsp").forward(request, response);
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
