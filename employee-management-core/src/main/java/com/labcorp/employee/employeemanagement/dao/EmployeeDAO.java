package com.labcorp.employee.employeemanagement.dao;

import com.labcorp.employee.employeemanagement.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDAO {
    Optional<List<Employee>> getAllEmployees();
    Optional<Employee> getEmployeeById(UUID id);
    void save(Employee employee);
}
