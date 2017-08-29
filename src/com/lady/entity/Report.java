package com.lady.entity;

public class Report {
    private String name;
    private int people;
    private double hours;
    private int cost;

    public Report(){}

    public Report(String name, int people, double hours, int cost) {
        this.name = name;
        this.people = people;
        this.hours = hours;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
