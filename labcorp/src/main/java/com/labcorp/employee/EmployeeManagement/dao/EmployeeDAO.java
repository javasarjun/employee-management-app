package com.labcorp.employee.EmployeeManagement.dao;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import com.labcorp.employee.EmployeeManagement.model.SalariedEmployee;
import org.springframework.stereotype.Repository;

import com.labcorp.employee.EmployeeManagement.model.Employee;
import com.labcorp.employee.EmployeeManagement.model.HourlyEmployee;
import com.labcorp.employee.EmployeeManagement.model.Manager;

@Repository
public class EmployeeDAO {
	
	private static Map<UUID, Employee> employeeRepo = new ConcurrentHashMap<>();

    static
    {
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(x -> {
                    UUID uuid = UUID.randomUUID();
                    employeeRepo.put(uuid, new HourlyEmployee("HE" + x, uuid));
                });
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(x -> {
                    UUID uuid = UUID.randomUUID();
                    employeeRepo.put(uuid, new SalariedEmployee("SE" + x, uuid));
                });
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(x -> {
                    UUID uuid = UUID.randomUUID();
                    employeeRepo.put(uuid, new Manager("MA" + x, uuid));
                });
    } 

    public Optional<List<Employee>> getAllEmployees()
    { 
        return Optional.ofNullable(new ArrayList<>(employeeRepo.values()));
    } 
    
    
    public Optional<Employee> getEmployeeById(UUID id) {
    	return Optional.ofNullable(employeeRepo.get(id));
    }
  
}
