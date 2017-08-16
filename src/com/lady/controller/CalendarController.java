package com.lady.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.lady.dao.EmployeeDAO;
import com.lady.dao.RestDAO;
import com.lady.dao.SpecialDAO;
import com.lady.dao.TransferToDAO;
import com.lady.entity.Employee;
import com.lady.entity.NewTransferTo;
import com.lady.entity.Rest;
import com.lady.entity.RestJsonFormatDTO;
import com.lady.entity.Special;
import com.lady.factory.DAOFactory;
import com.lady.util.ConvertToMonthNumber;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalendarController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] monthsName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//		FOR MSSQL
//		DAOFactory MSSQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MSSQL);
		DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();
		EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
		
//		從session取得Employee ID
		HttpSession session = request.getSession();
		int employeeId = ((Integer) session.getAttribute("employeeId")).intValue();
		
		request.setCharacterEncoding("utf-8");
		
		String reqDate = request.getParameter("date");
		String reqType = request.getParameter("type");
		String reqDescription = request.getParameter("description");
		String reqCommand = request.getParameter("command");
		String reqYear = request.getParameter("year");
		String reqMonth = request.getParameter("month");
		String reqJustWatch = request.getParameter("justWatch");
		
		if(reqCommand != null) {
			int restId = restDAO.findRestId(employeeId, reqDate);
			if(reqCommand.equals("update")) {
				restDAO.deleteRest(restId);
				Rest rest = new Rest(0, reqDate, reqType, reqDescription, employeeId, 0, 0);
				restDAO.insertRest(rest);
			}
			else {
				restDAO.deleteRest(restId);
			}
		} 
		else {
			if(reqDate != null) {
				Rest rest = new Rest(0, reqDate, reqType, reqDescription, employeeId, 0, 0);
				restDAO.insertRest(rest);
			}
		}
		
		String month;
		String[] date;
		String[] type;
		String[] description;
		String[] restChangedBy;
		
		String[] transferToCounter;
		String[] addedBy;
		String[] changedBy;
		String[] specialOnTime;
		String[] specialOffTime;
		
		int currentMonth = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		if(reqYear != null) {
			currentMonth = ConvertToMonthNumber.convertFrom(reqMonth);
			calendar.set(Integer.valueOf(reqYear), currentMonth, 1);
		}
		else if(reqJustWatch == null){
			calendar.add(Calendar.MONTH, 1);
//			calendar.set(2017, 10, 1);
		}
		
		month = calendar.get(Calendar.YEAR) + "  " + monthsName[calendar.get(Calendar.MONTH)];
		
		List<Rest> rest = restDAO.findRestFromEmployeeId(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employeeId);
		List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employeeId);
		List<Special> specials = specialDAO.findSpecialFromEmployeeId2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), employeeId);
		
		date = new String[rest.size()];
		type = new String[rest.size()];
		description = new String[rest.size()];
		transferToCounter = new String[rest.size()];
		addedBy = new String[rest.size()];
		changedBy = new String[rest.size()];
		specialOnTime = new String[rest.size()];
		specialOffTime = new String[rest.size()];
		restChangedBy = new String[rest.size()];
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("[");
		
		for(int i=0; i<rest.size(); i++) {
			if(rest.get(i).getRestDate() == "") {
				date[i] = "";
				type[i] = "";
				description[i] = "";
				restChangedBy[i] = "";
			}
			else {
				date[i] = rest.get(i).getRestDate();
				type[i] = rest.get(i).getRestType();
				description[i] = rest.get(i).getRestDescription();
				if(rest.get(i).getChangedBy() != 0) {
					Employee employee = employeeDAO.selectEmployee(rest.get(i).getChangedBy());
					String empName = employee.getEmployeeName();
					restChangedBy[i] = empName;
				}
				else {
					restChangedBy[i] = "";
				}
				
			}
			
			if(newTransferTos.get(i).getAddedBy().equals("")) {
				transferToCounter[i] = "";
				addedBy[i] = "";
			}
			else {
				transferToCounter[i] = newTransferTos.get(i).getCounterName();
				addedBy[i] = newTransferTos.get(i).getAddedBy();
			}
			
			if(specials.get(i).getChangedBy() == 0) {
				changedBy[i] = "";
				specialOnTime[i] = "";
				specialOffTime[i] = "";
			}
			else {
				Employee employee = employeeDAO.selectEmployee(specials.get(i).getChangedBy());
				String empName = employee.getEmployeeName();
				changedBy[i] = empName;
				specialOnTime[i] = specials.get(i).getOnTime();
				specialOffTime[i] = specials.get(i).getOffTime();
			}
			
			RestJsonFormatDTO restJsonFormat = new RestJsonFormatDTO(month, date[i], type[i], description[i], transferToCounter[i], addedBy[i], changedBy[i], specialOnTime[i], specialOffTime[i], restChangedBy[i]);
			String json = new Gson().toJson(restJsonFormat);
			response.getWriter().write(json);
			
			if(i != rest.size()-1) {
				response.getWriter().write(",");
			}
		}
		response.getWriter().write("]");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
