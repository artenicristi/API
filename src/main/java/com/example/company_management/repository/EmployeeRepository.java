package com.example.company_management.repository;

import com.example.company_management.entity.Employee;
import com.example.company_management.entity.EmployeeDetails;
import com.example.company_management.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CompanyRepository companyRepository;

    public List<Employee> findAll() {

        List<Employee> list = jdbcTemplate.query("SELECT id, first_name, last_name, email, salary FROM employee",
                (response, rowNum) -> {
                    return new Employee(
                            response.getInt("id"),
                            response.getString("first_name"),
                            response.getString("last_name"),
                            response.getString("email"),
                            response.getInt("salary")
                    );
                });

        return list;
    }

    public EmployeeDetails findDetailsByID(int id) {

        EmployeeDetails employeeDetails = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = ?",
                new Object[]{id},
                (response, rowNum) -> {
                    return new EmployeeDetails(
                            response.getInt("id"),
                            response.getString("first_name"),
                            response.getString("last_name"),
                            response.getString("email"),
                            response.getString("birth_date"),
                            response.getInt("salary"),
                            response.getInt("manager_id"),
                            response.getInt("company_id")
                    );
                });


        if (employeeDetails.getManagerId() != 0)
            employeeDetails.setManager(this.findByID(employeeDetails.getManagerId()));

        if (employeeDetails.getCompanyId() != 0)
            employeeDetails.setCompany(companyRepository.findByID(employeeDetails.getCompanyId()));

        return employeeDetails;
    }

    public Employee findByID(int id) {

        Employee employee = jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email, salary FROM employee WHERE id = ?",
                new Object[]{id},
                (response, rowNum) -> {
                    return new Employee(
                            response.getInt("id"),
                            response.getString("first_name"),
                            response.getString("last_name"),
                            response.getString("email"),
                            response.getInt("salary")
                    );
                });

        return employee;
    }

    public Manager findManagerList(int id) {

        Manager managerList = new Manager();

        List<Integer> list = jdbcTemplate.query("SELECT id FROM employee WHERE manager_id = ?",
                new Object[]{id},
                (response, rowNum) -> {
                    return response.getInt("id");
                });

        managerList.setEmployee(this.findByID(id));

        for (int i = 0; i < list.size(); i ++)
            managerList.getEmployeeList().add(this.findDetailsByID(list.get(i)));

        return managerList;
    }

    public List<Employee> findInSalaryRange(int minSalary, int maxSalary) {

        List<Employee> list = jdbcTemplate.query("SELECT id, first_name, last_name, email, salary FROM employee WHERE salary BETWEEN ? AND ?",
                new Object[]{minSalary, maxSalary},
                (response, rowNum) -> {
                    return new Employee(
                            response.getInt("id"),
                            response.getString("first_name"),
                            response.getString("last_name"),
                            response.getString("email"),
                            response.getInt("salary")
                    );
                });

        return list;
    }

    public List<Employee> findByEmail(String email) {

        List<Employee> list = jdbcTemplate.query("SELECT id, first_name, last_name, email, salary FROM employee WHERE email LIKE '%" + email + "'",
                (response, rowNum) -> {
                    return new Employee(
                            response.getInt("id"),
                            response.getString("first_name"),
                            response.getString("last_name"),
                            response.getString("email"),
                            response.getInt("salary")
                    );
                });

        return list;
    }

    public void save(EmployeeDetails employeeDetails) {
        jdbcTemplate.update("INSERT INTO employee (id, first_name, last_name, email, birth_date, salary, manager_id, company_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{
                        employeeDetails.getId(),
                        employeeDetails.getFirstName(),
                        employeeDetails.getLastName(),
                        employeeDetails.getEmail(),
                        employeeDetails.getBirthDate(),
                        employeeDetails.getSalary(),
                        employeeDetails.getManagerId() == 0 ? null : employeeDetails.getManagerId(),
                        employeeDetails.getCompanyId() == 0 ? null : employeeDetails.getCompanyId()
                });
    }

    public void update(int id, EmployeeDetails employeeDetails) {
        jdbcTemplate.update("UPDATE employee SET first_name = ?, last_name = ?, email = ?, birth_date = ?, " +
                        "salary = ?, manager_id = ?, company_id = ? WHERE id = ? ",
                new Object[]{
                        employeeDetails.getFirstName(),
                        employeeDetails.getLastName(),
                        employeeDetails.getEmail(),
                        employeeDetails.getBirthDate(),
                        employeeDetails.getSalary(),
                        employeeDetails.getManagerId() == 0 ? null : employeeDetails.getManagerId(),
                        employeeDetails.getCompanyId() == 0 ? null : employeeDetails.getCompanyId(),
                        id
                });
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM employee WHERE id = ?",
                new Object[]{id});
    }
}
