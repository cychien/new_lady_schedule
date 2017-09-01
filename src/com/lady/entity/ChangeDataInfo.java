package com.lady.entity;

public class ChangeDataInfo {
    private int employeeId;
    private String employeeName;
    private String startDate;
    private int month;
    private int year;
    private int businessBonus;
    private int targetBonus;
    private int managementBonus;
    private int yearBonus;
    private int otherBonus;
    private int supplementMinus;
    private int chargeMinus;
    private int violationMinus;
    private int buyMinus;
    private int phoneMinus;
    private int checkMinus;
    private int borrowMinus;
    private int courtMinus;
    private int otherMinus;
    private int salesPerformance;

    public ChangeDataInfo(){}

    public ChangeDataInfo(int employeeId, String employeeName, String startDate, int month, int year, int businessBonus, int targetBonus, int managementBonus, int yearBonus, int otherBonus, int supplementMinus, int chargeMinus, int violationMinus, int buyMinus, int phoneMinus, int checkMinus, int borrowMinus, int courtMinus, int otherMinus, int salesPerformance) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.month = month;
        this.year = year;
        this.businessBonus = businessBonus;
        this.targetBonus = targetBonus;
        this.managementBonus = managementBonus;
        this.yearBonus = yearBonus;
        this.otherBonus = otherBonus;
        this.supplementMinus = supplementMinus;
        this.chargeMinus = chargeMinus;
        this.violationMinus = violationMinus;
        this.buyMinus = buyMinus;
        this.phoneMinus = phoneMinus;
        this.checkMinus = checkMinus;
        this.borrowMinus = borrowMinus;
        this.courtMinus = courtMinus;
        this.otherMinus = otherMinus;
        this.salesPerformance = salesPerformance;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBusinessBonus() {
        return businessBonus;
    }

    public void setBusinessBonus(int businessBonus) {
        this.businessBonus = businessBonus;
    }

    public int getTargetBonus() {
        return targetBonus;
    }

    public void setTargetBonus(int targetBonus) {
        this.targetBonus = targetBonus;
    }

    public int getManagementBonus() {
        return managementBonus;
    }

    public void setManagementBonus(int managementBonus) {
        this.managementBonus = managementBonus;
    }

    public int getYearBonus() {
        return yearBonus;
    }

    public void setYearBonus(int yearBonus) {
        this.yearBonus = yearBonus;
    }

    public int getOtherBonus() {
        return otherBonus;
    }

    public void setOtherBonus(int otherBonus) {
        this.otherBonus = otherBonus;
    }

    public int getSupplementMinus() {
        return supplementMinus;
    }

    public void setSupplementMinus(int supplementMinus) {
        this.supplementMinus = supplementMinus;
    }

    public int getChargeMinus() {
        return chargeMinus;
    }

    public void setChargeMinus(int chargeMinus) {
        this.chargeMinus = chargeMinus;
    }

    public int getViolationMinus() {
        return violationMinus;
    }

    public void setViolationMinus(int violationMinus) {
        this.violationMinus = violationMinus;
    }

    public int getBuyMinus() {
        return buyMinus;
    }

    public void setBuyMinus(int buyMinus) {
        this.buyMinus = buyMinus;
    }

    public int getPhoneMinus() {
        return phoneMinus;
    }

    public void setPhoneMinus(int phoneMinus) {
        this.phoneMinus = phoneMinus;
    }

    public int getCheckMinus() {
        return checkMinus;
    }

    public void setCheckMinus(int checkMinus) {
        this.checkMinus = checkMinus;
    }

    public int getBorrowMinus() {
        return borrowMinus;
    }

    public void setBorrowMinus(int borrowMinus) {
        this.borrowMinus = borrowMinus;
    }

    public int getCourtMinus() {
        return courtMinus;
    }

    public void setCourtMinus(int courtMinus) {
        this.courtMinus = courtMinus;
    }

    public int getOtherMinus() {
        return otherMinus;
    }

    public void setOtherMinus(int otherMinus) {
        this.otherMinus = otherMinus;
    }

    public int getSalesPerformance() {
        return salesPerformance;
    }

    public void setSalesPerformance(int salesPerformance) {
        this.salesPerformance = salesPerformance;
    }
}
