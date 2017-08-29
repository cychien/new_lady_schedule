package com.lady.entity;

public class BASalary {
    private String payMethod;
    private String name;
    //本薪
    private int base;
    //獎金
    private int money;
    //總薪水
    private int salary;
    //加班費
    private int overtime;
    //績效獎金
    private int performanceBonus;
    //教育老師
    private int educationBonus;
    //主櫃津貼
    private int ownerBonus;
    //津貼
    private int allowance;
    //代扣勞健
    private int insuranceMinus;
    //投保金額
    private int insurance;

    public BASalary(){}

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public BASalary(String payMethod, String name, int base, int money, int salary, int overtime, int performanceBonus, int educationBonus, int ownerBonus, int allowance, int insuranceMinus, int insurance) {
        this.payMethod = payMethod;

        this.name = name;
        this.base = base;
        this.money = money;
        this.salary = salary;
        this.overtime = overtime;
        this.performanceBonus = performanceBonus;
        this.educationBonus = educationBonus;
        this.ownerBonus = ownerBonus;
        this.allowance = allowance;
        this.insuranceMinus = insuranceMinus;
        this.insurance = insurance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return base;
    }

    public void setYear(int base) {
        this.base = base;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
