package com.lady.entity;

public class Bonus {
    private int employeeId;
    private String employeeName;
    private int month;
    private int money;
    private int modify;
    private int year;

    public Bonus(){}

    public Bonus(int employeeId, String employeeName, int month, int money, int modify, int year) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.month = month;
        this.money = money;
        this.modify = modify;
        this.year = year;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getModify() {
        return modify;
    }

    public void setModify(int modify) {
        this.modify = modify;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
