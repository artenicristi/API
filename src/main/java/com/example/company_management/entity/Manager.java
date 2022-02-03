package com.example.company_management.entity;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private Employee employee;
    private List<EmployeeDetails> employeeList;

    public Manager() {
        this.employee = null;
        this.employeeList = new ArrayList<>();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<EmployeeDetails> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDetails> employeeList) {
        this.employeeList = employeeList;
    }
}
