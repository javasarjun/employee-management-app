package com.labcorp.employee.employeemanagement.utils;

import com.labcorp.employee.employeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseUtility {

    @Autowired
    private Map<UUID, Employee> employeeData;

    public Optional<List<Employee>> getAllEmployees() {
        return Optional.ofNullable(new ArrayList<>(employeeData.values()));
    }

    public Optional<Employee> getEmployeeById(UUID id) {
        return Optional.ofNullable(employeeData.get(id));
    }

    public void save(Employee employee) {
        employeeData.put(employee.getId(), employee);
    }
}
