package com.lady.entity;

public class PaySummaryInfo {
    private String time;
    private String employeeName;
    private int base;
    private int performanceBonus;
    private int educationBonus;
    private int ownerBonus;
    private int allowance;
    private int bonus;
    private int insuranceMinus;
    private int insurance;

    public PaySummaryInfo() {}

    public PaySummaryInfo(String time, String employeeName, int base, int performanceBonus, int educationBonus, int ownerBonus, int allowance, int bonus, int insuranceMinus, int insurance) {
        this.time = time;
        this.employeeName = employeeName;
        this.base = base;
        this.performanceBonus = performanceBonus;
        this.educationBonus = educationBonus;
        this.ownerBonus = ownerBonus;
        this.allowance = allowance;
        this.bonus = bonus;
        this.insuranceMinus = insuranceMinus;
        this.insurance = insurance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getPerformanceBonus() {
        return performanceBonus;
    }

    public void setPerformanceBonus(int performanceBonus) {
        this.performanceBonus = performanceBonus;
    }

    public int getEducationBonus() {
        return educationBonus;
    }

    public void setEducationBonus(int educationBonus) {
        this.educationBonus = educationBonus;
    }

    public int getOwnerBonus() {
        return ownerBonus;
    }

    public void setOwnerBonus(int ownerBonus) {
        this.ownerBonus = ownerBonus;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getInsuranceMinus() {
        return insuranceMinus;
    }

    public void setInsuranceMinus(int insuranceMinus) {
        this.insuranceMinus = insuranceMinus;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }
}
