package com.lady.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lady.dao.CounterDAO;
import com.lady.dao.RestDAO;
import com.lady.dao.SpecialDAO;
import com.lady.dao.TransferToDAO;
import com.lady.entity.Rest;
import com.lady.entity.Special;
import com.lady.entity.TransferTo;
import com.lady.factory.DAOFactory;
import com.lady.util.SomeMethod;

/**
 * Servlet implementation class UpdateShift
 */
@WebServlet("/UpdateShift")
public class UpdateShift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShift() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
//		FOR MySQL
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		
		String pk = request.getParameter("pk");
		String value = request.getParameter("value");
		HttpSession session = request.getSession();
		int teamLeaderId = (int) session.getAttribute("employeeId");
		int areaId = (int) session.getAttribute("areaId");
		String[] pkArr = pk.split("-");
		if(pkArr.length == 4) {
			int employeeId = Integer.valueOf(pkArr[0]);
			String date = pkArr[1] + "-" + pkArr[2] + "-" + pkArr[3];
			if(value.equals("0")) {
				int repeat2 = specialDAO.checkRepeat(date, employeeId);
				if(repeat2 == 1) {
					specialDAO.deleteSpecial(date, employeeId);
				}
				int repeat = transferToDAO.check(date, employeeId);
				if(repeat == 1) {
					transferToDAO.deleteTransferTo(date, employeeId, 0);
				}
				else if(repeat == 0) {
					int restId = restDAO.findRestId(employeeId, date);
					restDAO.deleteRest(restId);
				}
			}
			else if(value.equals("/")) {
				int restId = restDAO.findRestId(employeeId, date);
				if(restId != 0) {
					restDAO.deleteRest(restId);
				}
				int repeat2 = specialDAO.checkRepeat(date, employeeId);
				if(repeat2 == 1) {
					specialDAO.deleteSpecial(date, employeeId);
				}
				int repeat = transferToDAO.check(date, employeeId);
				if(repeat == 1) {
					transferToDAO.deleteTransferTo(date, employeeId, 0);
				}
				Rest rest = new Rest(0, date, "事假", "", employeeId, 1, teamLeaderId);
				restDAO.insertRest(rest);
			}
			else if(value.equals("01") || value.equals("02") || value.equals("03") || value.equals("04") || value.equals("05") || value.equals("06") || value.equals("07")) {
				int restId = restDAO.findRestId(employeeId, date);
				if(restId != 0) {
					restDAO.deleteRest(restId);
				}
				int repeat2 = specialDAO.checkRepeat(date, employeeId);
				if(repeat2 == 1) {
					specialDAO.deleteSpecial(date, employeeId);
				}
				int repeat = transferToDAO.check(date, employeeId);
				if(repeat == 1) {
					transferToDAO.deleteTransferTo(date, employeeId, 0);
				}
				String restType = SomeMethod.convertToRestName(value);
				if(restType != null) {
					Rest rest = new Rest(0, date, restType, "", employeeId, 1, teamLeaderId);
					restDAO.insertRest(rest);
				}
			}
			else if(SomeMethod.isNumeric(value)){
				int restId = restDAO.findRestId(employeeId, date);
				if(restId != 0) {
					restDAO.deleteRest(restId);
				}
				int repeat2 = specialDAO.checkRepeat(date, employeeId);
				if(repeat2 == 1) {
					specialDAO.deleteSpecial(date, employeeId);
				}
				int repeat = transferToDAO.check(date, employeeId);
				if(repeat == 1) {
					transferToDAO.deleteTransferTo(date, employeeId, 0);
				}
				int counterId = Integer.valueOf(value);
//				if(counterDAO.isValid(areaId, counterId) == 1) {
					TransferTo transferTo = new TransferTo(date, employeeId, counterId, teamLeaderId);
					transferToDAO.insertTransferTo(transferTo);
//				}
			}
		}
		else {
			int employeeId = Integer.valueOf(pkArr[0]);
			String date = pkArr[1] + "-" + pkArr[2] + "-" + pkArr[3];
			String type = pkArr[4];
			Special special;
			if(type.equals("on")) 
				special = new Special(date, employeeId, 0, value, "", teamLeaderId);
			else 
				special = new Special(date, employeeId, 0, "", value, teamLeaderId);
			specialDAO.insertSpecial(special);
		}
//		System.out.println(pk);
//		System.out.println(value);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
