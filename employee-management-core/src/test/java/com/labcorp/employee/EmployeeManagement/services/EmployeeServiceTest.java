package com.labcorp.employee.EmployeeManagement.services;

import com.labcorp.employee.EmployeeManagement.dao.EmployeeDAO;

import com.labcorp.employee.EmployeeManagement.exception.EmployeeNotFoundException;
import com.labcorp.employee.EmployeeManagement.services.impl.EmployeeServiceImpl;
import com.labcorp.employee.EmployeeManagement.model.Employee;
import com.labcorp.employee.EmployeeManagement.model.HourlyEmployee;
import com.labcorp.employee.EmployeeManagement.model.Manager;
import com.labcorp.employee.EmployeeManagement.model.SalariedEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
 class EmployeeServiceTest {

    private EmployeeService employeeService;

    private List<Employee> allEmployees;


    @MockBean
    private EmployeeDAO employeeDAO;

    @MockBean
    private Employee employee;

    @BeforeEach
     void before() {
        employeeService = new EmployeeServiceImpl(employeeDAO);

        Employee hourly = new HourlyEmployee("HE 1", UUID.randomUUID());
        Employee salary = new SalariedEmployee("SE 1", UUID.randomUUID());
        Employee manager = new Manager("MA 1", UUID.randomUUID());

        allEmployees = Arrays.asList(hourly, salary, manager);

    }

    @Test
     void getAllEmployeesTest3Records() {
        Mockito.when (employeeDAO.getAllEmployees()).thenReturn(Optional.of(allEmployees));

        List<Employee> actual =  employeeService.getEmployees();

        assertEquals(3, actual.size());

        Mockito
                .verify(employeeDAO, times(1))
                .getAllEmployees();

    }

    @Test
     void setWorkDaysTestInvalidEmployeeId() {
        Mockito.when (employeeDAO.getEmployeeById(employee.getId())).thenReturn(Optional.empty());

       assertThrows(EmployeeNotFoundException.class, () -> employeeService.setWorkDays(employee.getId(), 10));
    }

    @Test
     void setWorkDaysTestSuccess() {
        Mockito.when (employeeDAO.getEmployeeById(employee.getId())).thenReturn(Optional.of(employee));
        doNothing().when (employee).work(260);
        Employee updateEmployee = employeeService.setWorkDays(employee.getId(), 260);
        Mockito.verify(employeeDAO, times(1)).getEmployeeById(employee.getId());
        Mockito.verify(employee, times(1)).work(260);
    }

    @Test
     void setVacationDaysTestInvalidEmployeeId() {
        Mockito.when (employeeDAO.getEmployeeById(employee.getId())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.setVacationDays(employee.getId(), 1f);
        });
    }

    @Test
    public void setVacationDaysTestSuccess() {
        Mockito.when (employeeDAO.getEmployeeById(employee.getId())).thenReturn(Optional.of(employee));
        doNothing().when (employee).takeVacation(1f);
        Employee updateEmployee = employeeService.setVacationDays(employee.getId(), 1f);
        Mockito.verify(employeeDAO, times(1)).getEmployeeById(employee.getId());
        Mockito.verify(employee, times(1)).takeVacation(1f);
    }

}
