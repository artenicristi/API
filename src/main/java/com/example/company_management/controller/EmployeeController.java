package com.example.company_management.controller;

import com.example.company_management.entity.Employee;
import com.example.company_management.entity.EmployeeDetails;
import com.example.company_management.entity.Manager;
import com.example.company_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getList() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable("id") int id) {
        return employeeRepository.findByID(id);
    }

    @GetMapping("/details/{id}")
    public EmployeeDetails getEmployeeDetailsByID(@PathVariable("id") int id) {
        return employeeRepository.findDetailsByID(id);
    }

    @GetMapping("/manager/{id}")
    public Manager getManagerList(@PathVariable("id") int id) {
        return employeeRepository.findManagerList(id);
    }

    @GetMapping("/salary")
    public List<Employee> getInSalaryRange(@RequestParam("minSalary") int minSalary, @RequestParam("maxSalary") int maxSalary) {
        return employeeRepository.findInSalaryRange(minSalary, maxSalary);
    }

    @GetMapping("/email/{email}")
    public List<Employee> getListByEmail(@PathVariable("email") String email) {
        return employeeRepository.findByEmail(email);
    }

    @PostMapping
    public void save(@RequestBody EmployeeDetails employeeDetails) {
        employeeRepository.save(employeeDetails);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody EmployeeDetails employeeDetails) {
        employeeRepository.update(id, employeeDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        employeeRepository.delete(id);
    }


}
