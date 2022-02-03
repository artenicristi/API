package com.example.company_management.controller;

import com.example.company_management.entity.Company;
import com.example.company_management.entity.CompanyEmployeeList;
import com.example.company_management.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public List<Company> getList() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getByID(@PathVariable("id") int id) {
        return companyRepository.findByID(id);
    }

    @GetMapping("/name/{name}")
    public Company getByName(@PathVariable("name") String name) {
        return companyRepository.findByName(name);
    }

    @GetMapping("/employee_list/{id}")
    public CompanyEmployeeList getCompanyEmployeeList(@PathVariable("id") int id) {
        return companyRepository.findEmployeeList(id);
    }

    @GetMapping("/city/{city}")
    public List<Company> getListByCity(@PathVariable("city") String city) {
        return companyRepository.findByCity(city);
    }

    @PostMapping
    public void save(@RequestBody Company company) {
        companyRepository.save(company);
    }

    @PutMapping("/{name}")
    public void update(@PathVariable("name") String name, @RequestBody Company company) {
        companyRepository.update(name, company);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable("name") String name) {
        companyRepository.delete(name);
    }
}
