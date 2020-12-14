package com.labcorp.employee.employeemanagement.services;

import com.labcorp.employee.employeemanagement.dao.EmployeeDAO;

import com.labcorp.employee.employeemanagement.exception.EmployeeNotFoundException;
import com.labcorp.employee.employeemanagement.services.impl.EmployeeServiceImpl;
import com.labcorp.employee.employeemanagement.model.Employee;
import com.labcorp.employee.employeemanagement.model.HourlyEmployee;
import com.labcorp.employee.employeemanagement.model.Manager;
import com.labcorp.employee.employeemanagement.model.SalariedEmployee;
import com.labcorp.employee.employeemanagement.utils.VacationDaysUtility;
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
    private VacationDaysUtility vacationDaysUtility;


    private Employee hourly;

    @BeforeEach
     void before() {
        employeeService = new EmployeeServiceImpl(employeeDAO, vacationDaysUtility);
        hourly = new HourlyEmployee("HE 1", UUID.randomUUID());
        allEmployees = Arrays.asList(hourly, new SalariedEmployee("SE 1", UUID.randomUUID()), new Manager("MA 1", UUID.randomUUID()));
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
        Mockito.when (employeeDAO.getEmployeeById(hourly.getId())).thenReturn(Optional.empty());

       assertThrows(EmployeeNotFoundException.class, () -> employeeService.setWorkDays(hourly.getId(), 10));
    }

    @Test
     void setWorkDaysTestSuccess() {
        Mockito.when (employeeDAO.getEmployeeById(hourly.getId())).thenReturn(Optional.of(hourly));
        doNothing().when (vacationDaysUtility).work(260, hourly);
        Employee updateEmployee = employeeService.setWorkDays(hourly.getId(), 260);
        Mockito.verify(employeeDAO, times(1)).getEmployeeById(hourly.getId());
        Mockito.verify(vacationDaysUtility, times(1)).work(260, hourly);;
    }

    @Test
     void setVacationDaysTestInvalidEmployeeId() {
        Mockito.when (employeeDAO.getEmployeeById(hourly.getId())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.setVacationDays(hourly.getId(), 1f);
        });
    }

    @Test
    public void setVacationDaysTestSuccess() {
        Mockito.when (employeeDAO.getEmployeeById(hourly.getId())).thenReturn(Optional.of(hourly));
        doNothing().when (vacationDaysUtility).takeVacation(1f, hourly);
        Employee updateEmployee = employeeService.setVacationDays(hourly.getId(), 1f);
        Mockito.verify(employeeDAO, times(1)).getEmployeeById(hourly.getId());
        Mockito.verify(vacationDaysUtility, times(1)).takeVacation(1f, hourly);
    }

}
