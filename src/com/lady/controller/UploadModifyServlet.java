package com.lady.controller;

import com.lady.dao.BonusDAO;
import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.entity.Bonus;
import com.lady.factory.DAOFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UploadModifyServlet")
public class UploadModifyServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UploadModifyServlet () {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
        BonusDAO bonusDAO = MySQLDAOFactory.getBonusDAO();
        HttpSession session = request.getSession();
        int areaId = (int)session.getAttribute("areaId");
        String areaName = (String)session.getAttribute("areaName");

        try{
            int counterId = Integer.valueOf(request.getParameter("counter"));
            String counterName = counterDAO.findCounterName(counterId);
            String date = request.getParameter("date");
            int newMoney = Integer.valueOf(request.getParameter("money"));

            String[] monthNumber =  {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String month = monthNumber[calendar.get(Calendar.MONTH)];
            String fileName = "bonus" + String.valueOf(year) + month + areaName + ".csv";

            String currentFile = this.getServletContext().getRealPath("/confirmed") + "/" + fileName;
            File file = new File(currentFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            boolean rightPosition = false;
            boolean share = false;
            boolean firstTime = true;
            List<String> name = new ArrayList<>();
            List<Double> percent = new ArrayList<>();
            List<Integer> originMoney = new ArrayList<>();
            while(line != null) {
                String[] tmp = line.split(",");
                if(tmp.length == 6 && tmp[1].equals(counterName) && tmp[2].equals(date)) {
                    if(firstTime == true) {
                        name.clear();
                        percent.clear();
                        originMoney.clear();
                        firstTime = false;
                    }
                    for(int i=0; i<name.size(); i++) {
                        if(tmp[3].equals(name.get(i))) {
                            name.clear();
                            percent.clear();
                            originMoney.clear();
                        }
                    }

                    name.add(tmp[3]);
                    percent.add(Double.valueOf(tmp[4]));
                    originMoney.add(Integer.valueOf(tmp[5]));
                }
                if(share == true && tmp[1].equals("")) {
                    name.add(tmp[2]);
                    percent.add(Double.valueOf(tmp[3]));
                    originMoney.add(Integer.valueOf(tmp[4]));
                }
                else {
                    if(!tmp[0].equals(""))
                        rightPosition = false;
                    share = false;
                }
                if(tmp[0].equals(counterName)) {
                    rightPosition = true;
                }
                if(tmp[1].equals(date) && rightPosition == true) {
                    share = true;
                    name.add(tmp[2]);
                    percent.add(Double.valueOf(tmp[3]));
                    originMoney.add(Integer.valueOf(tmp[4]));
                }
                line = bufferedReader.readLine();
            }

            File file2 = new File(currentFile);
            FileWriter writer = new FileWriter(file2, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(int i=0; i<name.size(); i++) {
                int employeeId = employeeDAO.findEmployeeIdFromEmployeeName(name.get(i));
                bonusDAO.modifyBonus(employeeId, calendar.get(Calendar.MONTH)+1, originMoney.get(i), percent.get(i), newMoney, calendar.get(Calendar.YEAR));
                String content = "new" + "," + counterName + "," + date + "," + name.get(i) + "," + percent.get(i) + "," + String.valueOf(Math.round(newMoney*percent.get(i)));
                bufferedWriter.write(content);
                bufferedWriter.newLine();
            }

            if(bufferedReader != null)
                bufferedReader.close();
            if(fileReader != null)
                fileReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (writer != null)
                writer.close();

            request.setAttribute("modifyMessage", "修改完成");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("modifyMessage", "修改出錯");
        } finally {
            request.getRequestDispatcher("BonusCalculate").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}



