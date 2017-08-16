package com.lady.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.google.gson.Gson;
import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
//import com.lady.entity.Counter;
//import com.lady.entity.MatchInfoDTO;
import com.lady.dao.SpecialDAO;
import com.lady.dao.TransferToDAO;
import com.lady.entity.NewTransferTo;
import com.lady.entity.Special;
import com.lady.entity.TransferTo;
import com.lady.factory.DAOFactory;
import com.lady.factory.MSSQLDAOFactory;
import com.lady.util.SomeMethod;

/**
 * Servlet implementation class Match
 */
@WebServlet("/Match")
public class Match extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Match() {
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
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();
		Calendar calendar = Calendar.getInstance();

		HttpSession session = request.getSession();
		int areaId = (int) session.getAttribute("areaId");
		
		request.setCharacterEncoding("utf-8");
		String pk = request.getParameter("pk");
		String value = request.getParameter("value");
		String[] arr = pk.split("-");
		int employeeId = Integer.valueOf(arr[0]);
		
		if(SomeMethod.isNumeric(value)) {
			if(Integer.valueOf(value) == 1) {
				List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employeeId);
				for(int i=0; i<newTransferTos.size(); i++) {
					if(newTransferTos.get(i).getEmployeeId() != 0)
						transferToDAO.deleteTransferTo(newTransferTos.get(i).getTransferToDate(), employeeId, newTransferTos.get(i).getCounterId());
				}
				List<Special> specials = specialDAO.findSpecialFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employeeId);
				for(int i=0; i<specials.size(); i++) {
					if(specials.get(i).getEmployeeId() != 0)
						specialDAO.deleteSpecial(specials.get(i).getSpecialDate(), employeeId);
				}
				employeeDAO.match(employeeId, 0);
			}
			else if(counterDAO.isValid(areaId, Integer.valueOf(value)) == 1) {
				employeeDAO.match(employeeId, Integer.valueOf(value));
			}
		}
			
//		String command = request.getParameter("command");
//		
//		if(command != null) {
//			if(command.equals("modify")) {
//				List<MatchInfoDTO> matchInfoList = employeeDAO.findBA(areaId, 1, 1);
//				List<Counter> counterList = counterDAO.findCounterFromAreaId(areaId);
//				request.setAttribute("matchInfoList", matchInfoList);
//				request.setAttribute("modifyMode", 1);
//				request.setAttribute("counterList", counterList);
//			}
//			else if(command.equals("submit")) {
//				request.setCharacterEncoding("utf-8");
//				String json = request.getParameter("json");
//				Gson gson = new Gson();
//				MatchInfoDTO[] matchInfoDTOs = gson.fromJson(json, MatchInfoDTO[].class);
//				for(int i=0; i<matchInfoDTOs.length; i++) {
//					employeeDAO.match(matchInfoDTOs[i].getEmployeeId(), matchInfoDTOs[i].getCounterId());
//				}
//				List<MatchInfoDTO> matchInfoList = employeeDAO.findBA(areaId, 1, 1);
//				request.setAttribute("matchInfoList", matchInfoList);
//				request.setAttribute("modifyMode", 0);
//			}
//		}
//		else {
//			List<MatchInfoDTO> matchInfoList = employeeDAO.findBA(areaId, 1, 1);
//			request.setAttribute("matchInfoList", matchInfoList);
//			request.setAttribute("modifyMode", 0);
//		}
//		request.getRequestDispatcher("teamLeader2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
