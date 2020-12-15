package com.labcorp.employee.employeemanagement.config;

import com.labcorp.employee.employeemanagement.controller.EmployeeController;
import com.labcorp.employee.employeemanagement.model.Employee;
import com.labcorp.employee.employeemanagement.model.HourlyEmployee;
import com.labcorp.employee.employeemanagement.model.Manager;
import com.labcorp.employee.employeemanagement.model.SalariedEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Configuration
public class LoadEmployeeData {
    private static final Logger logger = LoggerFactory
            .getLogger(LoadEmployeeData.class);

    @Bean
    public Map<UUID, Employee> employeeData(){
        logger.info("Preloading customer");
        Map<UUID, Employee> employeeRepo = new ConcurrentHashMap<>();

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

        return employeeRepo;
    }

}
