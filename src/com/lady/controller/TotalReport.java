package com.lady.controller;

import com.lady.dao.*;
import com.lady.entity.*;
import com.lady.entity.ChangeData;
import com.lady.factory.DAOFactory;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TotalReport")
public class TotalReport extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    private static final String UPLOAD_DIRECTORY = "upload";

    public TotalReport() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
        CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
        BonusDAO bonusDAO = MySQLDAOFactory.getBonusDAO();
        ChangeDataDAO changeDataDAO = MySQLDAOFactory.getChangeDataDAO();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        List<Area> northAreas = new ArrayList<>();
        List<Area> centralAreas = new ArrayList<>();
        List<Area> southAreas = new ArrayList<>();
        List<Area> eastAreas = new ArrayList<>();
        List<Area> islandAreas = new ArrayList<>();

        List<Area> areas = areaDAO.readArea();
        for(int i=0; i<areas.size(); i++) {
            if(areas.get(i).getAreaRange().equals("北部"))
                northAreas.add(areas.get(i));
            else if(areas.get(i).getAreaRange().equals("中部"))
                centralAreas.add(areas.get(i));
            else if(areas.get(i).getAreaRange().equals("南部"))
                southAreas.add(areas.get(i));
            else if(areas.get(i).getAreaRange().equals("東部"))
                eastAreas.add(areas.get(i));
            else if(areas.get(i).getAreaRange().equals("離島"))
                islandAreas.add(areas.get(i));
        }

        int totalBA = 0;
        double totalOver = 0;
        int totalCost = 0;

        if(!northAreas.isEmpty()) {
            List<Report> north = new ArrayList<>();
            for(int i=0; i<northAreas.size(); i++) {
                List<BADTO> BAs = employeeDAO.findBAFromAreaId(northAreas.get(i).getAreaId());
                double totalOvertime = employeeDAO.calTotalOvertime(northAreas.get(i).getAreaId());
                int hourlySalary = 133;
                Report report = new Report(northAreas.get(i).getAreaName(), BAs.size(), totalOvertime, (int)Math.round(hourlySalary*totalOvertime));
                totalBA = totalBA + BAs.size();
                totalOver = totalOver + totalOvertime;
                totalCost = totalCost + (int)Math.round(hourlySalary*totalOvertime);
                north.add(report);
            }
            request.setAttribute("north", north);
        }
        if(!centralAreas.isEmpty()) {
            List<Report> central = new ArrayList<>();
            for(int i=0; i<centralAreas.size(); i++) {
                List<BADTO> BAs = employeeDAO.findBAFromAreaId(centralAreas.get(i).getAreaId());
                double totalOvertime = employeeDAO.calTotalOvertime(centralAreas.get(i).getAreaId());
                int hourlySalary = 133;
                Report report = new Report(centralAreas.get(i).getAreaName(), BAs.size(), totalOvertime, (int)Math.round(hourlySalary*totalOvertime));
                totalBA = totalBA + BAs.size();
                totalOver = totalOver + totalOvertime;
                totalCost = totalCost + (int)Math.round(hourlySalary*totalOvertime);
                central.add(report);
            }
            request.setAttribute("central", central);
        }
        if(!southAreas.isEmpty()) {
            List<Report> south = new ArrayList<>();
            for(int i=0; i<southAreas.size(); i++) {
                List<BADTO> BAs = employeeDAO.findBAFromAreaId(southAreas.get(i).getAreaId());
                double totalOvertime = employeeDAO.calTotalOvertime(southAreas.get(i).getAreaId());
                int hourlySalary = 133;
                Report report = new Report(southAreas.get(i).getAreaName(), BAs.size(), totalOvertime, (int)Math.round(hourlySalary*totalOvertime));
                totalBA = totalBA + BAs.size();
                totalOver = totalOver + totalOvertime;
                totalCost = totalCost + (int)Math.round(hourlySalary*totalOvertime);
                south.add(report);
            }
            request.setAttribute("south", south);
        }
        if(!eastAreas.isEmpty()) {
            List<Report> east = new ArrayList<>();
            for(int i=0; i<eastAreas.size(); i++) {
                List<BADTO> BAs = employeeDAO.findBAFromAreaId(eastAreas.get(i).getAreaId());
                double totalOvertime = employeeDAO.calTotalOvertime(eastAreas.get(i).getAreaId());
                int hourlySalary = 133;
                Report report = new Report(northAreas.get(i).getAreaName(), BAs.size(), totalOvertime, (int)Math.round(hourlySalary*totalOvertime));
                totalBA = totalBA + BAs.size();
                totalOver = totalOver + totalOvertime;
                totalCost = totalCost + (int)Math.round(hourlySalary*totalOvertime);
                east.add(report);
            }
            request.setAttribute("east", east);
        }
        if(!islandAreas.isEmpty()) {
            List<Report> island = new ArrayList<>();
            for(int i=0; i<islandAreas.size(); i++) {
                List<BADTO> BAs = employeeDAO.findBAFromAreaId(islandAreas.get(i).getAreaId());
                double totalOvertime = employeeDAO.calTotalOvertime(islandAreas.get(i).getAreaId());
                int hourlySalary = 133;
                Report report = new Report(islandAreas.get(i).getAreaName(), BAs.size(), totalOvertime, (int)Math.round(hourlySalary*totalOvertime));
                totalBA = totalBA + BAs.size();
                totalOver = totalOver + totalOvertime;
                totalCost = totalCost + (int)Math.round(hourlySalary*totalOvertime);
                island.add(report);
            }
            request.setAttribute("island", island);
        }
        Report totalReport = new Report("", totalBA, totalOver, totalCost);
        request.setAttribute("total", totalReport);

        List<Employee> employeeList = employeeDAO.findPeopleFromPosition2(1);
        for(int i=0; i<employeeList.size(); i++) {
            com.lady.entity.ChangeData changeData = changeDataDAO.findChangeDataFromEmployeeId(employeeList.get(i).getEmployeeID(), year,month);
            if(changeData == null) {
                com.lady.entity.ChangeData newChangeData = new com.lady.entity.ChangeData(employeeList.get(i).getEmployeeID(), month, year, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                changeDataDAO.insertChangeData(newChangeData);
            }
        }

        Map<String, List<PaySummaryInfo>> map = new HashMap<>();
        for(int i=0; i<areas.size(); i++) {
            List<PaySummaryInfo> list = new ArrayList<>();
            List<Employee> employees = employeeDAO.findBAPeopleFromAreaId(areas.get(i).getAreaId());
            for(int j=0; j<employees.size(); j++) {
                Employee employee = employeeDAO.selectEmployee(employees.get(j).getEmployeeID());
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

                //獎金和獎金修改
                int salary = bonusDAO.findBonusFromEmployeeIdAndMonth(employees.get(j).getEmployeeID(), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
                int modify = bonusDAO.readModify(employees.get(j).getEmployeeID(), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));

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

                if(employees.get(j).getPayMethod().equals("正職")) {
                    int base = employee.getBase();
                    int overtime = (int)Math.round(employeeDAO.calEmployeeOvertime(employees.get(j).getEmployeeID())*133);
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("約聘(日薪1: $1200/日)")) {
                    double[] result = employeeDAO.calEmployeeBasedOnDayPay(employee.getEmployeeID());
//                    result[0]是fullDay; result[1]是hour(視為加班時間)
                    double payPerHour = (Math.round(1200/11 * 1000.0)) / 1000.0;
                    int base = (int)(result[0]*1200);
                    int overtime = (int)Math.round(result[1]*payPerHour);
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("約聘(日薪2: $1300/日)")) {
                    double[] result = employeeDAO.calEmployeeBasedOnDayPay(employee.getEmployeeID());
//                    result[0]是fullDay; result[1]是hour(視為加班時間)
                    double payPerHour = (Math.round(1300/11 * 1000.0)) / 1000.0;
                    int base = (int)(result[0]*1300);
                    int overtime = (int)Math.round(result[1]*payPerHour);
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("約聘(時薪1: $100/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 100);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("約聘(時薪2: $125/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 125);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("約聘(時薪3: $130/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 130);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("外聘(時薪1: $100/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 100);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("外聘(時薪2: $125/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 125);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("外聘(時薪3: $130/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 130);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("工讀生(時薪1: $100/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 100);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("工讀生(時薪2: $125/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 125);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }

                else if(employees.get(j).getPayMethod().equals("工讀生(時薪3: $130/時)")) {
                    double totalHour = employeeDAO.calEmployeeBasedOnHourPay(employee.getEmployeeID());
                    int base = (int)Math.round(totalHour * 130);
                    int overtime = 0;
                    int totalSalary = base + overtime + performanceBonus + educationBonus + ownerBonus + allowance - insuranceMinus + salary + modify + businessBonus + targetBonus + managementBonus + yearBonus + otherBonus - supplementMinus - chargeMinus - violationMinus - buyMinus - phoneMinus - checkMinus - borrowMinus - courtMinus - otherMinus;
                    PaySummaryInfo info = new PaySummaryInfo(employeeId, employeeName, stratTime, payMethod, year, month, base, overtime, performanceBonus, educationBonus, ownerBonus, allowance, insuranceMinus, insurance, salary+modify, businessBonus, targetBonus, managementBonus, yearBonus, otherBonus, supplementMinus, chargeMinus, violationMinus, buyMinus, phoneMinus, checkMinus, borrowMinus, courtMinus, otherMinus, totalSalary);
                    list.add(info);
                }
            }
            map.put(areas.get(i).getAreaName(), list);
        }
        request.setAttribute("salaryMap", map);

        List<PaySummaryInfo> paySummaryInfos = employeeDAO.findPaySummaryInfo(year, month);
        if(paySummaryInfos.size() != 0)
            request.setAttribute("buttonDisabled", true);

        request.getRequestDispatcher("totalReport.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
