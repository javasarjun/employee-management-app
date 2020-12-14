package com.labcorp.employee.employeemanagement.dao.impl;

import java.util.*;

import com.labcorp.employee.employeemanagement.dao.EmployeeDAO;
import com.labcorp.employee.employeemanagement.utils.DatabaseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.labcorp.employee.employeemanagement.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    DatabaseUtility databaseUtility;

    public Optional<List<Employee>> getAllEmployees()
    {
        return databaseUtility.getAllEmployees();
    } 
    
    
    public Optional<Employee> getEmployeeById(UUID id) {
        return databaseUtility.getEmployeeById(id);

    }

    @Override
    public void save(Employee employee) {
        databaseUtility.save(employee);
    }


}
