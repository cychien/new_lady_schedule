package com.lady.controller;

import com.lady.dao.EmployeeDAO;
import com.lady.entity.Employee;
import com.lady.entity.PaySummaryInfo;
import com.lady.factory.DAOFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HistoryRecord")
public class HistoryRecord extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HistoryRecord() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String queryYear = request.getParameter("year");
       String queryMonth = request.getParameter("month");
       DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
       EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
       if(queryMonth == null) {
           Calendar calendar = Calendar.getInstance();
           String[] monthNumberArray = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
           String year = String.valueOf(calendar.get(Calendar.YEAR));
           String monthNumber = monthNumberArray[calendar.get(Calendar.MONTH)];
           String time = year + "-" + monthNumber;

           List<PaySummaryInfo> paySummaryInfo = employeeDAO.findPaySummaryInfo(time);
           request.setAttribute("buttonAppear", "yes");
           request.setAttribute("paySummaryInfo", paySummaryInfo);
       }
       else {
           String[] monthNumber = {"x", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
           queryMonth = monthNumber[Integer.valueOf(queryMonth)];
           String time = queryYear + queryMonth;
           List<PaySummaryInfo> paySummaryInfo = employeeDAO.findPaySummaryInfo(time);
           if(paySummaryInfo == null)
               request.setAttribute("message", "查無資料");
           request.setAttribute("paySummaryInfo", paySummaryInfo);
       }
       request.getRequestDispatcher("historyRecord.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
