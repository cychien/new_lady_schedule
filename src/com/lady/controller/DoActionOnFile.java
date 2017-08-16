package com.lady.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lady.dao.*;
import com.lady.entity.*;
import com.lady.factory.DAOFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/DoActionOnFile")
public class DoActionOnFile extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    private static final String UPLOAD_DIRECTORY = "upload";

    public DoActionOnFile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory MySQLDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);
        CounterDAO counterDAO = MySQLDAOFactory.getCounterDAO();
        EmployeeDAO employeeDAO = MySQLDAOFactory.getEmployeeDAO();
        RestDAO restDAO = MySQLDAOFactory.getRestDAO();
        TransferToDAO transferToDAO = MySQLDAOFactory.getTransferToDAO();
        SpecialDAO specialDAO = MySQLDAOFactory.getSpecialDAO();

        String[] monthNumber =  {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String month = monthNumber[calendar.get(Calendar.MONTH)];
        HttpSession session = request.getSession();
        int areaId = (int)session.getAttribute("areaId");
        String areaName = (String)session.getAttribute("areaName");
        String fileName = "bonus" + String.valueOf(year) + month + areaName + ".csv";
        boolean repeat = false;

        String currentFile = this.getServletContext().getRealPath("/upload") + "/" + fileName;
        String savePath = this.getServletContext().getRealPath("/confirmed");
        String saveFile = this.getServletContext().getRealPath("/confirmed") + "/" + fileName;
        File saveDir = new File(savePath);
        if(!saveDir.exists())
            saveDir.mkdir();

        String command = request.getParameter("command");
        if(command.equals("delete")) {
            File file = new File(currentFile);
            file.delete();
        }
        else if(command.equals("confirm")){
            List<Counter> counters = counterDAO.findCounterFromAreaId(areaId);
            for(int i=0; i<counters.size(); i++) {

                List<Double> bonus = new ArrayList<>();
                //讀檔
                File file = new File(currentFile);
                BufferedReader bReader = null;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(currentFile),"UTF-8");
                bReader = new BufferedReader(isr);
                String line = bReader.readLine();
                String[] contentArr;
                boolean isEnd = true;
                int lineNumber = 1;
                while(line != null){
                    if(lineNumber == 1) {
                        line = line.substring(1);
                        lineNumber++;
                    }
                    contentArr = line.split(",");
                    System.out.println(contentArr[0]);
                    System.out.println(counters.get(i).getCounterName());
                    if(contentArr[0].equals(counters.get(i).getCounterName())) {
                        isEnd = false;
                    }
                    if(!contentArr[0].equals("") && !contentArr[0].equals(counters.get(i).getCounterName())) {
                        isEnd = true;
                    }
                    if(isEnd == false) {
                        bonus.add(Double.parseDouble(contentArr[2]));
                    }
                    line = bReader.readLine();
                }
                if(bReader != null)
                    bReader.close();

                String counterName = counters.get(i).getCounterName();
                for(int j=1; j<=calendar.getActualMaximum(Calendar.DAY_OF_MONTH); j++) {
                    String day = String.valueOf(j);
                    if(j < 10)
                        day = "0" + day;
                    String queryString = String.valueOf(year) + "-" + month + "-" + day;
                    List<MatchInfoDTO> employees = employeeDAO.findBA(areaId, 1, 1);
                    List<String> workers = new ArrayList<>();
                    List<Double> hours = new ArrayList<>();
                    double total = 0;
                    for(int k=0; k<employees.size(); k++) {
                        if (employees.get(k).getCounterId() == counters.get(i).getCounterID()) {
                            double workHours = 0;
                            boolean join = true;
                            boolean isTransfer = false;
                            double onTime = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(queryString, counters.get(i).getCounterID()));
                            double offTime = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(queryString, counters.get(i).getCounterID()));
                            List<Rest> rests = restDAO.findRestFromEmployeeId2(year, calendar.get(Calendar.MONTH), employees.get(k).getEmployeeId());
                            for (int m=0; m<rests.size(); m++) {
                                if(!rests.get(m).getRestType().equals("")) {
                                    if(rests.get(m).getRestDate().equals(queryString)) {
                                        onTime = 0;
                                        offTime = 0;
                                        join = false;
                                        break;
                                    }
                                }
                            }
                            List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(year, calendar.get(Calendar.MONTH), employees.get(k).getEmployeeId());
                            for (int m=0; m<newTransferTos.size(); m++) {
                                if(newTransferTos.get(m).getCounterId() != 0) {
                                    if(newTransferTos.get(m).getTransferToDate().equals(queryString)) {
                                        join = false;
                                        isTransfer = true;
                                        onTime = 0;
                                        offTime = 0;
                                        break;
                                    }
                                }
                            }
                            if(isTransfer == false) {
                                List<Special> specials = specialDAO.findSpecialFromEmployeeId(year, calendar.get(Calendar.MONTH), employees.get(k).getEmployeeId());
                                for (int m=0; m<specials.size(); m++) {
                                    if(specials.get(m).getEmployeeId() != 0) {
                                        if(specials.get(m).getSpecialDate().equals(queryString)) {
                                            String speOn= specials.get(m).getOnTime();
                                            String speOff= specials.get(m).getOffTime();
                                            if(!speOn.equals(""))
                                                onTime = Double.parseDouble(speOn);
                                            if(!speOff.equals(""))
                                                offTime = Double.parseDouble(speOff);
                                        }
                                    }
                                }
                            }
                            if(join == true) {
                                workHours = offTime - onTime;
                                if (onTime <= 11 && offTime >= 19)
                                    workHours = workHours - 1;
                                else if (onTime <= 11 && offTime >= 17 && offTime < 19)
                                    workHours = workHours - 0.5;
                                else if (onTime <= 11 && offTime >= 13 && offTime <= 17)
                                    workHours = workHours - 0.5;
                                else if (onTime > 11 && onTime <= 13 && offTime >= 19)
                                    workHours = workHours - 0.5;
                                else if (onTime >= 13 && onTime <= 17 && offTime >= 19)
                                    workHours = workHours - 0.5;
                                workers.add(employees.get(k).getEmployeeName());
                                hours.add(workHours);
                                total = total + workHours;
                            }
                        }
                        else {
                            boolean join = false;
                            double workHours = 0;
                            double onTime = 0;
                            double offTime = 0;
                            List<NewTransferTo> newTransferTos = transferToDAO.findTransferToFromEmployeeId(year, calendar.get(Calendar.MONTH), employees.get(k).getEmployeeId());
                            for (int m=0; m<newTransferTos.size(); m++) {
                                if(newTransferTos.get(m).getCounterId() != 0) {
                                    if(newTransferTos.get(m).getTransferToDate().equals(queryString) && newTransferTos.get(m).getCounterId()==counters.get(i).getCounterID()) {
                                        join = true;
                                        onTime = Double.parseDouble(counterDAO.findOnTimeFromDateAndId(queryString, counters.get(i).getCounterID()));
                                        offTime = Double.parseDouble(counterDAO.findOffTimeFromDateAndId(queryString, counters.get(i).getCounterID()));
                                        List<Special> specials = specialDAO.findSpecialFromEmployeeId(year, calendar.get(Calendar.MONTH), employees.get(k).getEmployeeId());
                                        for(int n=0; n<specials.size(); n++) {
                                            if(specials.get(n).getEmployeeId() != 0) {
                                                if(specials.get(n).getSpecialDate().equals(queryString)) {
                                                    String speOn= specials.get(n).getOnTime();
                                                    String speOff= specials.get(n).getOffTime();
                                                    if(!speOn.equals(""))
                                                        onTime = Double.parseDouble(speOn);
                                                    if(!speOff.equals(""))
                                                        offTime = Double.parseDouble(speOff);
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            if(join == true) {
                                workHours = offTime - onTime;
                                if (onTime <= 11 && offTime >= 19)
                                    workHours = workHours - 1;
                                else if (onTime <= 11 && offTime >= 17 && offTime < 19)
                                    workHours = workHours - 0.5;
                                else if (onTime <= 11 && offTime >= 13 && offTime <= 17)
                                    workHours = workHours - 0.5;
                                else if (onTime > 11 && onTime <= 13 && offTime >= 19)
                                    workHours = workHours - 0.5;
                                else if (onTime >= 13 && onTime <= 17 && offTime >= 19)
                                    workHours = workHours - 0.5;
                                workers.add(employees.get(k).getEmployeeName());
                                hours.add(workHours);
                                total = total + workHours;
                            }
                        }
                    }
                    System.out.println(workers.size());
                    System.out.println(hours.size());
                    System.out.println(bonus.size());
                    File file2 = new File(saveFile);
                    FileWriter writer = new FileWriter(file2, true);
                    BufferedWriter bufferedWriter = null;
                    bufferedWriter = new BufferedWriter(writer);
                    for(int k=0; k<workers.size(); k++) {
                        double percent = (Math.round(hours.get(k)/total*10.0))/10.0;
                        String content = counterName + "," + queryString + "," + workers.get(k) + "," + String.valueOf(percent) + "," + Math.round((bonus.get(j-1))*percent);
                        counterName = "";
                        queryString = "";
                        bufferedWriter.write(content);
//                        writer.write(content + "\r\n");
                        bufferedWriter.newLine();
                    }
                    if (bufferedWriter != null)
                        bufferedWriter.close();
                    if(writer != null)
                        writer.close();
                }
            }
            request.setAttribute("isSuccess", "檔案輸出成功!");
        }
        request.getRequestDispatcher("bonusCalculate.jsp").forward(request, response);
    }
}
