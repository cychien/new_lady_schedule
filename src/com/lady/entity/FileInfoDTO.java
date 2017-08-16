package com.lady.entity;

public class FileInfoDTO {
    private String counterName;
    private String date;
    private String bonus;

    public FileInfoDTO(){}

    public FileInfoDTO(String counterName, String date, String bonus) {
        this.counterName = counterName;
        this.date = date;
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

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
}
