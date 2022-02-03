package com.example.company_management.repository;

import com.example.company_management.entity.Company;
import com.example.company_management.entity.CompanyEmployeeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Lazy
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Company> findAll() {

        List<Company> list = jdbcTemplate.query("SELECT * FROM company",
                (response, rowNum) -> {
                    Company company = new Company(
                            response.getInt("id"),
                            response.getString("name"),
                            response.getString("city"),
                            response.getString("manager_begin_date"),
                            response.getInt("manager_id")
                    );

                    if (company.getManagerId() != 0)
                        company.setManager(employeeRepository.findByID(company.getManagerId()));

                    return company;
                });

        return list;
    }

    public Company findByID(int id) {

        Company companyByID = jdbcTemplate.queryForObject("SELECT * FROM company WHERE id = ?",
                new Object[]{id},
                (response, rowNum) -> {
                    Company company = new Company(
                            response.getInt("id"),
                            response.getString("name"),
                            response.getString("city"),
                            response.getString("manager_begin_date"),
                            response.getInt("manager_id")
                    );

                    if (company.getManagerId() != 0)
                        company.setManager(employeeRepository.findByID(company.getManagerId()));

                    return company;
                });

        return companyByID;
    }

    public Company findByName(String name) {

        Company companyByName = jdbcTemplate.queryForObject("SELECT * FROM company WHERE name = ?",
                new Object[]{name},
                (response, rowNum) -> {
                    Company company = new Company(
                            response.getInt("id"),
                            response.getString("name"),
                            response.getString("city"),
                            response.getString("manager_begin_date"),
                            response.getInt("manager_id")
                    );

                    if (company.getManagerId() != 0)
                        company.setManager(employeeRepository.findByID(company.getManagerId()));

                    return company;
                });

        return companyByName;
    }

    public CompanyEmployeeList findEmployeeList(int id) {

        CompanyEmployeeList companyEmployeeList = new CompanyEmployeeList();

        List<Integer> list = jdbcTemplate.query("SELECT id FROM employee WHERE company_id = ?",
                new Object[]{id},
                (response, rowNum) ->{
                    return response.getInt(("id"));
                });

        companyEmployeeList.setCompany(this.findByID(id));

        for (int i = 0; i < list.size(); i ++)
            companyEmployeeList.getEmployeeList().add(employeeRepository.findByID(list.get(i)));

        return companyEmployeeList;
    }

    public List<Company> findByCity(String city) {

        List<Company> list = jdbcTemplate.query("SELECT * FROM company WHERE city = ?",
                new Object[]{city},
                (response, rowNum) -> {
                    Company company = new Company(
                            response.getInt("id"),
                            response.getString("name"),
                            response.getString("city"),
                            response.getString("manager_begin_date"),
                            response.getInt("manager_id")
                    );

                    if (company.getManagerId() != 0)
                        company.setManager(employeeRepository.findByID(company.getManagerId()));

                    return company;
                });

        return list;
    }

    public void save(Company company) {
        jdbcTemplate.update("INSERT INTO company (id, name, city, manager_begin_date, manager_id) VALUES (?, ?, ?, ?, ?)",
                new Object[]{
                        company.getId(),
                        company.getName(),
                        company.getCity(),
                        company.getManagerBeginDate(),
                        company.getManagerId() == 0 ? null : company.getManagerId()
                });
    }

    public void update(String name, Company company) {
        jdbcTemplate.update("UPDATE company SET name = ?, city = ?, manager_begin_date = ?, manager_id = ? WHERE name = ?",
                new Object[]{
                        company.getName(),
                        company.getCity(),
                        company.getManagerBeginDate(),
                        company.getManagerId() == 0 ? null : company.getManagerId(),
                        name
                });
    }

    public void delete(String name) {
        jdbcTemplate.update("DELETE FROM company WHERE name = ?",
                new Object[]{name});
    }
}
