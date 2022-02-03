package com.example.company_management.entity;

import java.util.ArrayList;
import java.util.List;

public class CompanyEmployeeList {

    private Company company;
    private List<Employee> employeeList;

    public CompanyEmployeeList() {
        this.company = null;
        this.employeeList = new ArrayList<>();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
