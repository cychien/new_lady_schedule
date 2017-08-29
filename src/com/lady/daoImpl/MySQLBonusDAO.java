package com.lady.daoImpl;

import com.lady.dao.BonusDAO;
import com.lady.entity.Bonus;
import com.lady.util.DBConnection2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLBonusDAO implements BonusDAO {

    @Override
    public int findBonusFromEmployeeIdAndMonth(int employeeId, int month, int year) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnection2.createConnection();
            stmt = con.prepareStatement("SELECT * FROM BONUS WHERE Emp_ID=? AND Month=? AND Year=?");
            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            rs = stmt.executeQuery();
            if(rs.next())
                result = rs.getInt("Money");
        } catch (SQLException e) {
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
            return result;
        }
    }

    @Override
    public void insertBonus(Bonus bonus) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection2.createConnection();
            int money = findBonusFromEmployeeIdAndMonth(bonus.getEmployeeId(), bonus.getMonth(), bonus.getYear());
            if(money != 0) {
                stmt = con.prepareStatement("UPDATE BONUS SET Money = Money + ? WHERE Emp_ID = ? and Month = ? AND Year=?");
                stmt.setInt(1, bonus.getMoney());
                stmt.setInt(2, bonus.getEmployeeId());
                stmt.setInt(3, bonus.getMonth());
                stmt.setInt(4, bonus.getYear());
                stmt.execute();
            }
            else {
                stmt = con.prepareStatement("INSERT INTO BONUS (Emp_ID, Emp_Name, Month, Money, Modify, Year) VALUES (?, ?, ?, ?, 0, ?)");
                stmt.setInt(1, bonus.getEmployeeId());
                stmt.setString(2, bonus.getEmployeeName());
                stmt.setInt(3, bonus.getMonth());
                stmt.setInt(4, bonus.getMoney());
                stmt.setInt(5, bonus.getYear());
                stmt.execute();
            }
        } catch (SQLException e) {
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

    public void modifyBonus(int employeeId, int month, int originMoney, double percent, int newMoney, int year) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnection2.createConnection();
            int money = (int)Math.round(newMoney * percent);
            int gap = money - originMoney;
            stmt = con.prepareStatement("UPDATE BONUS SET Modify = Modify + ? WHERE Emp_ID = ? and Month = ? and Year=?");
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

    public int readModify(int employeeId, int month, int year) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnection2.createConnection();
            stmt = con.prepareStatement("SELECT * FROM BONUS WHERE Emp_ID = ? and Month = ? AND YEAR=?");
            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            rs = stmt.executeQuery();
            if(rs.next())
                result = rs.getInt("Modify");
        } catch(Exception e){
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
            return result;
        }
    }
}
