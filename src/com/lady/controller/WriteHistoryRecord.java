package com.lady.controller;

import com.lady.dao.BonusDAO;
import com.lady.dao.ChangeDataDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.dao.PaySummaryInfoDAO;
import com.lady.entity.BASalary;
import com.lady.entity.ChangeData;
import com.lady.entity.Employee;
import com.lady.entity.PaySummaryInfo;
import com.lady.factory.DAOFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        ChangeDataDAO changeDataDAO = MySQLDAOFactory.getChangeDataDAO();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        List<PaySummaryInfo> paySummaryInfoList = employeeDAO.findPaySummaryInfo(year, month);
        if(paySummaryInfoList.size() > 0)
            response.sendRedirect("TotalReport");
        else {
            List<Employee> employees = employeeDAO.findPeopleFromPosition2(1);

            for(int i=0; i<employees.size(); i++) {
                Employee employee = employees.get(i);

                int employeeId = employee.getEmployeeID();
                String employeeName = employee.getEmployeeName();
                String stratTime = employee.getStartDate();
                String payMethod = employee.getPayMethod();
                int performanceBonus = employee.getPerformanceBonus();
                int educationBonus = employee.getEducationBonus();
                int ownerBonus = employee.getOwnerBonus();
                int allowance = employee.getAllowance();
                int insuranceMinus = employee.getInsuranceMinus();
                int insurance = employee.getInsurance();

                //算獎金
                int salary = bonusDAO.findBonusFromEmployeeIdAndMonth(employee.getEmployeeID(), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
                int modify = bonusDAO.readModify(employee.getEmployeeID(), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
                int bonus = salary + modify;

                ChangeData BAChangeData = changeDataDAO.findChangeDataFromEmployeeId(employeeId, year, month);
                int businessBonus = BAChangeData.getBusinessBonus();
                int targetBonus = BAChangeData.getTargetBonus();
                int managementBonus = BAChangeData.getManagementBonus();
                int yearBonus = BAChangeData.getYearBonus();
                int otherBonus = BAChangeData.getOtherBonus();
                int supplementMinus = BAChangeData.getSupplementMinus();
                int chargeMinus = BAChangeData.getChargeMinus();
                int violationMinus = BAChangeData.getViolationMinus();
                int buyMinus = BAChangeData.getBuyMinus();
                int phoneMinus = BAChangeData.getPhoneMinus();
                int checkMinus = BAChangeData.getCheckMinus();
                int borrowMinus = BAChangeData.getBorrowMinus();
                int courtMinus = BAChangeData.getCourtMinus();
                int otherMinus = BAChangeData.getOtherMinus();

                if(employee.getPayMethod().equals("正職")) {
                    int base = employee.getBase();
                    int overtime = (int)Math.round(employeeDAO.calEmployeeOvertime(employee.getEmployeeID())*133);
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("約聘(日薪1: $1200/日)")) {
                    double[] result = employeeDAO.calEmployeeBasedOnDayPay(employee.getEmployeeID());
//                    result[0]是fullDay; result[1]是hour(視為加班時間)
                    double payPerHour = (Math.round(1200/11 * 1000.0)) / 1000.0;
                    int base = (int)(result[0]*1200);
                    int overtime = (int)Math.round(result[1]*payPerHour);
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("約聘(日薪2: $1300/日)")) {
                    double[] result = employeeDAO.calEmployeeBasedOnDayPay(employee.getEmployeeID());
//                    result[0]是fullDay; result[1]是hour(視為加班時間)
                    double payPerHour = (Math.round(1300/11 * 1000.0)) / 1000.0;
                    int base = (int)(result[0]*1300);
                    int overtime = (int)Math.round(result[1]*payPerHour);
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("約聘(時薪1: $100/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 100);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("約聘(時薪2: $125/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 125);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("約聘(時薪3: $130/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 130);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("外聘(時薪1: $100/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 100);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("外聘(時薪2: $125/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 125);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("外聘(時薪3: $130/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 130);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("工讀生(時薪1: $100/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 100);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("工讀生(時薪2: $125/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 125);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }

                else if(employee.getPayMethod().equals("工讀生(時薪3: $130/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 130);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    paySummaryInfoDAO.insertPaySummaryInfo(info);
                }
            }

            //輸出csv檔
            String savePath = this.getServletContext().getRealPath("/salary");
            File saveDir = new File(savePath);
            if(!saveDir.exists())
                saveDir.mkdir();

            String fileName = String.valueOf(year) + "." + String.valueOf(month) + "薪水表.csv";
            String saveFile = this.getServletContext().getRealPath("/salary") + "/" + fileName;
            File file = new File(saveFile);
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            List<PaySummaryInfo> paySummaryInfos = employeeDAO.findPaySummaryInfo(year, month);
            for(int i=0; i<paySummaryInfos.size(); i++) {
                PaySummaryInfo paySummaryInfo = paySummaryInfos.get(i);
                String content = paySummaryInfo.getEmployeeName() + "," + paySummaryInfo.getStartTime() + "," + paySummaryInfo.getBase() + ",," + paySummaryInfo.getOvertime() + "," + paySummaryInfo.getBonus() + "," + paySummaryInfo.getTargetBonus() + "," + paySummaryInfo.getManagementBonus() + "," + paySummaryInfo.getPerformanceBonus() + "," + paySummaryInfo.getYearBonus() + "," + paySummaryInfo.getBusinessBonus() + "," + paySummaryInfo.getEducationBonus() + "," + paySummaryInfo.getOwnerBonus() + "," + paySummaryInfo.getAllowance() + "," + paySummaryInfo.getOtherBonus() + "," + paySummaryInfo.getInsuranceMinus() + "," + paySummaryInfo.getSupplementMinus() + "," + paySummaryInfo.getChargeMinus() + "," + paySummaryInfo.getViolationMinus() + "," + paySummaryInfo.getBuyMinus() + "," + paySummaryInfo.getPhoneMinus() + "," + paySummaryInfo.getCheckMinus() + "," + paySummaryInfo.getBorrowMinus() + "," + paySummaryInfo.getCourtMinus() + "," + paySummaryInfo.getOtherMinus() + "," + paySummaryInfo.getSalary() + ",," + paySummaryInfo.getInsurance();
                bufferedWriter.write(content);
                bufferedWriter.newLine();
            }
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (writer != null)
                writer.close();
            request.setAttribute("saveSuccess", "存檔完成");
            request.getRequestDispatcher("TotalReport").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
