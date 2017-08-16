package com.lady.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.dao.RestDAO;
import com.lady.dao.SpecialDAO;
import com.lady.dao.TransferToDAO;
import com.lady.entity.AllShiftInfoDTO;
import com.lady.entity.MatchInfoDTO;
import com.lady.entity.NewTransferTo;
import com.lady.entity.Rest;
import com.lady.entity.Special;
import com.lady.factory.DAOFactory;

/**
 * Servlet implementation class ProduceAllShiftInfo
 */
@WebServlet("/ProduceAllShiftInfo")
public class ProduceAllShiftInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduceAllShiftInfo() {
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
		RestDAO restDAO = MySQLDAOFactory.getRestDAO();
		TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
		CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
		SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();
		
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, 1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		HttpSession session = request.getSession();
		int areaId = (int) session.getAttribute("areaId");
		
		List<MatchInfoDTO> matchInfoDTOs = employeeDAO.findBA(areaId, 1, 1);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("[");
		
		for(int i=0; i<matchInfoDTOs.size(); i++) {
			List<Rest> restList = new ArrayList<>();
			restList = restDAO.findRestFromEmployeeId2(year, month, matchInfoDTOs.get(i).getEmployeeId());
			Rest[] restArray = new Rest[restList.size()];
			restArray = restList.toArray(restArray);
			List<NewTransferTo> transferToList= new ArrayList<NewTransferTo>();
			transferToList = transferToDAO.findTransferToFromEmployeeId(year, month, matchInfoDTOs.get(i).getEmployeeId());
			NewTransferTo[] transferToArray = new NewTransferTo[transferToList.size()];
			transferToArray = transferToList.toArray(transferToArray);
			
			String[] workTime = new String[7];
			String[] onTime = {"", "", "", "", "", "", ""};
			String[] offTime = {"", "", "", "", "", "", ""};
			if(matchInfoDTOs.get(i).getCounterId() != 0) {
				workTime = counterDAO.getWorkTime(matchInfoDTOs.get(i).getCounterId());
				for(int j=0; j<7; j++) {
					String[] arr = workTime[j].split("-");
					onTime[j] = arr[0];
					offTime[j] = arr[1];
				}
			}
			
			List<String> nextMonth = new ArrayList<>();
			String[] weekdays ={"X", "日","一","二","三","四","五","六"};
			Calendar calendar2 = Calendar.getInstance();
//			calendar2.add(Calendar.MONTH, 1);
			calendar2.set(Calendar.DAY_OF_MONTH, 1);
			int dates = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
			int weekdayIndex = calendar2.get(Calendar.DAY_OF_WEEK);
			for(int k=weekdayIndex; k<weekdayIndex+dates; k++) {
				int j = k % 7;
				if(j == 0)
					j = 7;
				nextMonth.add(weekdays[j]);
			}
			String[] weekday = new String[nextMonth.size()];
			weekday = nextMonth.toArray(weekday);
			
			List<Special> specialList = new ArrayList<>();
			specialList = specialDAO.findSpecialFromEmployeeId(year, month, matchInfoDTOs.get(i).getEmployeeId());
			Special[] specialArray = new Special[specialList.size()];
			specialArray = specialList.toArray(specialArray);
			
			AllShiftInfoDTO allShiftInfoDTO = new AllShiftInfoDTO(matchInfoDTOs.get(i), restArray, transferToArray, onTime, offTime, weekday, specialArray);
		
			String json = new Gson().toJson(allShiftInfoDTO);
			response.getWriter().write(json);
			
			if(i != matchInfoDTOs.size()-1) {
				response.getWriter().write(",");
			}
		}
		response.getWriter().write("]");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
