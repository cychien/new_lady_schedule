package com.lady.daoImpl;

import com.lady.dao.PaySummaryInfoDAO;
import com.lady.entity.PaySummaryInfo;
import com.lady.util.DBConnection2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLPaySummaryInfoDAO implements PaySummaryInfoDAO {

    public void insertPaySummaryInfo(PaySummaryInfo paySummaryInfo) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection2.createConnection();
            stmt = con.prepareStatement("INSERT INTO PaySummaryInfo (Emp_ID, Emp_Name, Start_Time, Pay_Method, Year, MONTH, Base, Overtime, Performance_Bonus, Education_Bonus, Owner_Bonus, Allowance, insuranceMinus, Insurance, Bonus, Business_Bonus, Target_Bonus, Management_Bonus, Year_Bonus, Other_Bonus, Supplement_Minus, Charge_Minus, Violation_Minus, Buy_Minus, Phone_Minus, Check_Minus, Borrow_Minus, Court_Minus, Other_Minus, Salary) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, paySummaryInfo.getEmployeeId());
            stmt.setString(2, paySummaryInfo.getEmployeeName());
            stmt.setString(3, paySummaryInfo.getStartTime());
            stmt.setString(4, paySummaryInfo.getPayMethod());
            stmt.setInt(5, paySummaryInfo.getYear());
            stmt.setInt(6, paySummaryInfo.getMonth());
            stmt.setInt(7, paySummaryInfo.getBase());
            stmt.setInt(8, paySummaryInfo.getOvertime());
            stmt.setInt(9, paySummaryInfo.getPerformanceBonus());
            stmt.setInt(10, paySummaryInfo.getEducationBonus());
            stmt.setInt(11, paySummaryInfo.getOwnerBonus());
            stmt.setInt(12, paySummaryInfo.getAllowance());
            stmt.setInt(13, paySummaryInfo.getInsuranceMinus());
            stmt.setInt(14, paySummaryInfo.getInsurance());
            stmt.setInt(15, paySummaryInfo.getBonus());
            stmt.setInt(16, paySummaryInfo.getBusinessBonus());
            stmt.setInt(17, paySummaryInfo.getTargetBonus());
            stmt.setInt(18, paySummaryInfo.getManagementBonus());
            stmt.setInt(19, paySummaryInfo.getYearBonus());
            stmt.setInt(20, paySummaryInfo.getOtherBonus());
            stmt.setInt(21, paySummaryInfo.getSupplementMinus());
            stmt.setInt(22, paySummaryInfo.getChargeMinus());
            stmt.setInt(23, paySummaryInfo.getViolationMinus());
            stmt.setInt(24, paySummaryInfo.getBuyMinus());
            stmt.setInt(25, paySummaryInfo.getPhoneMinus());
            stmt.setInt(26, paySummaryInfo.getCheckMinus());
            stmt.setInt(27, paySummaryInfo.getBorrowMinus());
            stmt.setInt(28, paySummaryInfo.getCourtMinus());
            stmt.setInt(29, paySummaryInfo.getOtherBonus());
            stmt.setInt(30, paySummaryInfo.getSalary());
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
    }
}
