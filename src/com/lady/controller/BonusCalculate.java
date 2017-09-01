package com.lady.controller;

import com.lady.dao.AreaDAO;
import com.lady.dao.CounterDAO;
import com.lady.entity.Area;
import com.lady.entity.Counter;
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

@WebServlet("/BonusCalculate")
public class BonusCalculate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BonusCalculate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
        AreaDAO areaDAO = MySQLDAOFactory.getAreaDAO();
        HttpSession session = request.getSession();
        int areaId = (int)session.getAttribute("selectedAreaId");

        List<Counter> counters = counterDAO.findCounterFromAreaId(areaId);

        List<String> date = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String[] monthNumber = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String month =  monthNumber[calendar.get(Calendar.MONTH)];
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=1; i<=days; i++) {
            String day = String.valueOf(i);
            if(i < 10)
                day = "0" + day;
            String dateString = String.valueOf(year) + "-" + month + "-" + day;
            date.add(dateString);
        }

        List<Area> areas = areaDAO.readArea();
        request.setAttribute("areas", areas);
        request.setAttribute("activeId", areaId);

        request.setAttribute("counters", counters);
        request.setAttribute("date", date);
        request.getRequestDispatcher("bonusCalculate.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
