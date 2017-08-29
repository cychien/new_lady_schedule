package com.lady.controller;

import com.lady.dao.BonusDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.dao.PaySummaryInfoDAO;
import com.lady.entity.BASalary;
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

@WebServlet("/WriteHistoryRecord")
public class WriteHistoryRecord extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WriteHistoryRecord() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        PaySummaryInfoDAO paySummaryInfoDAO = MySQLDAOFactory.getPaySummaryInfoDAO();
        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
        BonusDAO bonusDAO = MySQLDAOFactory.getBonusDAO();

        Calendar calendar = Calendar.getInstance();
        String[] monthNumberArray = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = monthNumberArray[calendar.get(Calendar.MONTH)];
        String date = year + "-" + month;

        List<Employee> employees = employeeDAO.findPeopleFromPosition2(1);

        for(int i=0; i<employees.size(); i++) {
            Employee employee = employees.get(i);

            //算獎金
            int salary = bonusDAO.findBonusFromEmployeeIdAndMonth(employee.getEmployeeID(), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
            int modify = bonusDAO.readModify(employee.getEmployeeID(), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
            int bonus = salary + modify;

            String employeeName = employee.getEmployeeName();
            int performanceBonus = employee.getPerformanceBonus();
            int educationBonus = employee.getEducationBonus();
            int ownerBonus = employee.getOwnerBonus();
            int allowance = employee.getAllowance();
            int insuranceMinus = employee.getInsuranceMinus();
            int insurance = employee.getInsurance();

            if(employee.getPayMethod().equals("正職")) {
                int base = employee.getBase();
                int overtime = (int)Math.round(employeeDAO.calEmployeeOvertime(employee.getEmployeeID())*133);
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("約聘(日薪1: $1200/日)")) {
                double[] result = employeeDAO.calEmployeeBasedOnDayPay(employee.getEmployeeID());
//                    result[0]是fullDay; result[1]是hour(視為加班時間)
                double payPerHour = (Math.round(1200/11 * 1000.0)) / 1000.0;
                int base = (int)(result[0]*1200);
                int overtime = (int)Math.round(result[1]*payPerHour);
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("約聘(日薪2: $1300/日)")) {
                double[] result = employeeDAO.calEmployeeBasedOnDayPay(employee.getEmployeeID());
//                    result[0]是fullDay; result[1]是hour(視為加班時間)
                double payPerHour = (Math.round(1300/11 * 1000.0)) / 1000.0;
                int base = (int)(result[0]*1300);
                int overtime = (int)Math.round(result[1]*payPerHour);
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("約聘(時薪1: $100/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 100);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("約聘(時薪2: $125/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 125);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("約聘(時薪3: $130/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 130);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("外聘(時薪1: $100/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 100);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("外聘(時薪2: $125/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 125);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("外聘(時薪3: $130/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 130);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("工讀生(時薪1: $100/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 100);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("工讀生(時薪2: $125/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 125);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }

            else if(employee.getPayMethod().equals("工讀生(時薪3: $130/時)")) {
                double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                int base = (int)Math.round(totalHour * 130);
                int overtime = 0;
                int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify;
                PaySummaryInfo paySummaryInfo = new PaySummaryInfo();
//                paySummaryInfoDAO.insertPaySummaryInfo(paySummaryInfo);
            }
        }
        request.setAttribute("saveSuccess", "存檔完成");
        request.getRequestDispatcher("historyRecord.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
