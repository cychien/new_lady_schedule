package com.lady.controller;

import com.google.gson.Gson;
import com.lady.dao.ChangeDataDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.ChangeDataInfo;
import com.lady.entity.Employee;
import com.lady.factory.DAOFactory;

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

@WebServlet("/ProduceChangeData")
public class ProduceChangeData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProduceChangeData() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
        ChangeDataDAO changeDataDAO = MySQLDAOFactory.getChangeDataDAO();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        HttpSession session = request.getSession();
        Object defend = session.getAttribute("positionId");
        int positionId = 0;
        if(defend != null) {
            positionId = (int)defend;
            if(positionId == 3) {
                List<ChangeDataInfo> list = new ArrayList<>();
                List<Employee> employees = employeeDAO.findPeopleFromPosition2(1);
                for(int i=0; i<employees.size(); i++) {
                    com.lady.entity.ChangeData changeData = changeDataDAO.findChangeDataFromEmployeeId(employees.get(i).getEmployeeID(), year,month+1);
                    if(changeData == null) {
                        com.lady.entity.ChangeData newChangeData = new com.lady.entity.ChangeData(employees.get(i).getEmployeeID(), month + 1, year, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                        changeDataDAO.insertChangeData(newChangeData);
                        ChangeDataInfo changeDataInfo = new ChangeDataInfo(employees.get(i).getEmployeeID(), employees.get(i).getEmployeeName(), employees.get(i).getStartDate(), month + 1, year, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                        list.add(changeDataInfo);
                    }
                    else {
                        ChangeDataInfo changeDataInfo = new ChangeDataInfo(employees.get(i).getEmployeeID(), employees.get(i).getEmployeeName(), employees.get(i).getStartDate(), month + 1, year, changeData.getBusinessBonus(), changeData.getTargetBonus(), changeData.getManagementBonus(), changeData.getYearBonus(), changeData.getOtherBonus(), changeData.getSupplementMinus(), changeData.getChargeMinus(), changeData.getViolationMinus(), changeData.getBuyMinus(), changeData.getPhoneMinus(), changeData.getCheckMinus(), changeData.getBorrowMinus(), changeData.getCourtMinus(), changeData.getOtherMinus(), changeData.getSalesPerformance());
                        list.add(changeDataInfo);
                    }
                }
                ChangeDataInfo[] changeDataInfos = new ChangeDataInfo[list.size()];
                changeDataInfos = list.toArray(changeDataInfos);

                String json = new Gson().toJson(changeDataInfos);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
            else
                response.sendRedirect("index.jsp");
        }
        else
            response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
