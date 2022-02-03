package com.example.company_management.entity;

public class Company {

    private int id;
    private String name;
    private String city;
    private String managerBeginDate;
    private int managerId;
    private Employee manager;

    public Company(int id,
                   String name,
                   String city,
                   String managerBeginDate,
                   int managerId) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.managerBeginDate = managerBeginDate;
        this.managerId = managerId;
        this.manager = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getManagerBeginDate() {
        return managerBeginDate;
    }

    public void setManagerBeginDate(String managerBeginDate) {
        this.managerBeginDate = managerBeginDate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
