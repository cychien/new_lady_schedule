package com.lady.daoImpl;

import com.lady.dao.ChangeDataDAO;
import com.lady.entity.ChangeData;
import com.lady.util.DBConnection2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLChangeDataDAO implements ChangeDataDAO{
    public ChangeData findChangeDataFromEmployeeId(int employeeId, int year, int month) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ChangeData changeData = null;
        try {
            con = DBConnection2.createConnection();
            stmt = con.prepareStatement("SELECT * from CHANGE_DATA where Emp_ID=? and Year=? and Month=?");
            stmt.setInt(1, employeeId);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            rs = stmt.executeQuery();
            if(rs.next())
                changeData = new ChangeData(rs.getInt("Emp_ID"), rs.getInt("Month"), rs.getInt("Year"), rs.getInt("Business_Bonus"), rs.getInt("Target_Bonus"), rs.getInt("Management_Bonus"), rs.getInt("Year_Bonus"), rs.getInt("Other_Bonus"), rs.getInt("Supplement_Minus"), rs.getInt("Charge_Minus"), rs.getInt("Violation_Minus"), rs.getInt("Buy_Minus"), rs.getInt("Phone_Minus"), rs.getInt("Check_Minus"), rs.getInt("Borrow_Minus"), rs.getInt("Court_Minus"), rs.getInt("Other_Minus"), rs.getInt("Sales_Performance"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
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
            return changeData;
        }
    }

    public void insertChangeData(ChangeData changeData) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection2.createConnection();
            stmt = con.prepareStatement("INSERT INTO CHANGE_DATA (Emp_ID, Month, Year, Business_Bonus, Target_Bonus, Management_Bonus, Year_Bonus, Other_Bonus, Supplement_Minus, Charge_Minus, Violation_Minus, Buy_Minus, Phone_Minus, Check_Minus, Borrow_Minus, Court_Minus, Other_Minus, Sales_Performance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, changeData.getEmployeeId());
            stmt.setInt(2, changeData.getMonth());
            stmt.setInt(3, changeData.getYear());
            stmt.setInt(4, changeData.getBusinessBonus());
            stmt.setInt(5, changeData.getTargetBonus());
            stmt.setInt(6, changeData.getManagementBonus());
            stmt.setInt(7, changeData.getYearBonus());
            stmt.setInt(8, changeData.getOtherBonus());
            stmt.setInt(9, changeData.getSupplementMinus());
            stmt.setInt(10, changeData.getChargeMinus());
            stmt.setInt(11, changeData.getViolationMinus());
            stmt.setInt(12, changeData.getBuyMinus());
            stmt.setInt(13, changeData.getPhoneMinus());
            stmt.setInt(14, changeData.getCheckMinus());
            stmt.setInt(15, changeData.getBorrowMinus());
            stmt.setInt(16, changeData.getCourtMinus());
            stmt.setInt(17, changeData.getOtherMinus());
            stmt.setInt(18, changeData.getSalesPerformance());
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

    public void modifyChangeData(int employeeId, String column, String newValue, int year, int month) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection2.createConnection();
            if(column.equals("businessBonus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Business_Bonus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("targetBonus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Target_Bonus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("managementBonus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Management_Bonus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("yearBonus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Year_Bonus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("otherBonus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Other_Bonus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("supplementMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Supplement_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("chargeMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Charge_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("violationMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Violation_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("buyMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Buy_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setString(1, newValue);
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("phoneMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Phone_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("checkMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Check_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("borrowMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Borrow_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("courtMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Court_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("otherMinus")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA set Other_Minus=? where Emp_ID=? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
            else if(column.equals("salesPerformance")) {
                stmt = con.prepareStatement("UPDATE CHANGE_DATA SET Sales_Performance = Sales_Performance + ? WHERE Emp_ID = ? and Year=? and Month=?");
                stmt.setInt(1, Integer.valueOf(newValue));
                stmt.setInt(2, employeeId);
                stmt.setInt(3, year);
                stmt.setInt(4, month);
                stmt.execute();
            }
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

    public void modifySalesPerformance(int employeeId, int month, int originMoney, double percent, int newMoney, int year) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection2.createConnection();
            int money = (int)Math.round(newMoney * percent);
            int gap = money - originMoney;
            stmt = con.prepareStatement("UPDATE CHANGE_DATA SET Sales_Performance = Sales_Performance + ? WHERE Emp_ID = ? and Month = ? and Year=?");
            stmt.setInt(1, gap);
            stmt.setInt(2, employeeId);
            stmt.setInt(3, month);
            stmt.setInt(4, year);
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
