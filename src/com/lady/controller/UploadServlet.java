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

import com.lady.entity.FileInfoDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    private static final String UPLOAD_DIRECTORY = "upload";

    public UploadServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] monthNumber =  {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String month = monthNumber[calendar.get(Calendar.MONTH)];
        HttpSession session = request.getSession();
        String areaName = (String)session.getAttribute("areaName");
        String fileName = "bonus" + String.valueOf(year) + month + areaName + ".csv";
        boolean repeat = false;

        String currentFile = this.getServletContext().getRealPath("/upload") + "/" + fileName;
        String savePath = this.getServletContext().getRealPath("/upload");
        File saveDir = new File(savePath);
        if(!saveDir.exists())
            saveDir.mkdir();

        for (String fName : saveDir.list()) {
            if(fName.equals(fileName)) {
                repeat = true;
                request.setAttribute("isRepeat", "該月份獎金表已上傳過，故無法再上傳");
                break;
            }
        }

        if(repeat == false) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(saveDir);
            factory.setSizeThreshold(1024 * 1024);

            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> fileItemList = (List<FileItem>)upload.parseRequest(request);
                for(FileItem item : fileItemList) {
                    String inputName = item.getFieldName();
                    if(item.isFormField()) {
                        String value = item.getString();
                        request.setAttribute(inputName, value);
                    }
                    else {
                        item.write(new File(savePath, fileName));
                    }
                }
                request.setAttribute("isOK", "確認資料後，請按送出");
                BufferedReader bufferedReader = null;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(currentFile),"UTF-8");
                bufferedReader = new BufferedReader(isr);
                String line = bufferedReader.readLine();
                List<FileInfoDTO> list = new ArrayList<>();
                String[] contentArr;
                while(line != null) {
                    line = line.replace("?", "");
                    contentArr = line.split(",");
                    FileInfoDTO fileInfoDTO = new FileInfoDTO(contentArr[0], contentArr[1], contentArr[2]);
                    list.add(fileInfoDTO);
                    line = bufferedReader.readLine();
                }

                request.setAttribute("fileInfo", list);

                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("bonusCalculate.jsp").forward(request, response);
    }
}
