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
       String queryYear = request.getParameter("control-year");
       String queryMonth = request.getParameter("control-month");
       DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
       EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
       if(queryMonth == null | queryYear == null) {
           Calendar calendar = Calendar.getInstance();
           int year = calendar.get(Calendar.YEAR);
           int month = calendar.get(Calendar.MONTH) + 1;

           List<PaySummaryInfo> paySummaryInfo = employeeDAO.findPaySummaryInfo(year, month);
           if(paySummaryInfo.size() == 0)
               request.setAttribute("message", "查無資料");
           request.setAttribute("year", year);
           request.setAttribute("month", month);
           request.setAttribute("paySummaryInfo", paySummaryInfo);
       }
       else {
           Calendar calendar = Calendar.getInstance();
           List<PaySummaryInfo> paySummaryInfo = employeeDAO.findPaySummaryInfo(Integer.valueOf(queryYear), Integer.valueOf(queryMonth));
           if(paySummaryInfo.size() == 0)
               request.setAttribute("message", "查無資料");
           request.setAttribute("year", Integer.valueOf(queryYear));
           request.setAttribute("month", Integer.valueOf(queryMonth));
           request.setAttribute("paySummaryInfo", paySummaryInfo);
       }
       request.getRequestDispatcher("historyRecord.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
