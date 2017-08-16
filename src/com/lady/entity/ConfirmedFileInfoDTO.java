package com.lady.entity;

public class ConfirmedFileInfoDTO {
    private String counterName;
    private String date;
    private String employeeName;
    private double proportion;
    private int bonus;

    public ConfirmedFileInfoDTO(){}

    public ConfirmedFileInfoDTO(String counterName, String date, String employeeName, double proportion, int bonus) {
        this.counterName = counterName;
        this.date = date;
        this.employeeName = employeeName;
        this.proportion = proportion;
        this.bonus = bonus;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
