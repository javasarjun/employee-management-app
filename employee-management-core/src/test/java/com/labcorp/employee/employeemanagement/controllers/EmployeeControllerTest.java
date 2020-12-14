package com.labcorp.employee.employeemanagement.controllers;

import com.labcorp.employee.employeemanagement.controller.EmployeeController;
import com.labcorp.employee.employeemanagement.model.Employee;
import com.labcorp.employee.employeemanagement.model.HourlyEmployee;
import com.labcorp.employee.employeemanagement.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(EmployeeController.class)
 class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetAllEmployees() {
        Employee employee = new HourlyEmployee("Test", UUID.randomUUID());

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        Mockito
                .when(employeeService.getEmployees())
                .thenReturn(employeeList);

        webTestClient.get().uri("/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class);

        Mockito
                .verify(employeeService, times(1))
                .getEmployees();

    }


    @Test
    void testSetWorkDays() {

        Employee employee = new HourlyEmployee("Test", UUID.fromString("e85ab6e5-7c41-45a5-8963-ec6158b6faad"));

        Mockito
                .when(employeeService.setWorkDays(UUID.fromString("e85ab6e5-7c41-45a5-8963-ec6158b6faad"), 260))
                .thenReturn(employee);

        webTestClient.put().uri("/employee/{id}/days-worked/{days}", "e85ab6e5-7c41-45a5-8963-ec6158b6faad", 260)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class);

        Mockito
                .verify(employeeService, times(1))
                .setWorkDays(UUID.fromString("e85ab6e5-7c41-45a5-8963-ec6158b6faad"), 260);
    }

    @Test
    void testSetWorkDaysTestCannotBeGreaterThanWorkYear() {
        webTestClient.put().uri("/employee/{id}/days-worked/{days}", "e85ab6e5-7c41-45a5-8963-ec6158b6faad", 261)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testSetWorkDaysCannotBeNegative() {
        webTestClient.put().uri("/employee/{id}/days-worked/{days}", "e85ab6e5-7c41-45a5-8963-ec6158b6faad", -1)
                .exchange()
                .expectStatus()
                .isBadRequest();

    }


    @Test
     void testSetVacationDays() {
        Employee employee = new HourlyEmployee("Test", UUID.fromString("e85ab6e5-7c41-45a5-8963-ec6158b6faad"));

        Mockito
                .when(employeeService.setVacationDays(UUID.fromString("e85ab6e5-7c41-45a5-8963-ec6158b6faad"), 1.0f))
                .thenReturn(employee);

        webTestClient.put().uri("/employee/{id}/take-vacation/{days}", "e85ab6e5-7c41-45a5-8963-ec6158b6faad", 1.0f)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class);

        Mockito
                .verify(employeeService, times(1))
                .setVacationDays(UUID.fromString("e85ab6e5-7c41-45a5-8963-ec6158b6faad"), 1.0f);
    }

    @Test
    void testSetVacationDaysCannotBeNegative() {
        webTestClient.put().uri("/employee/{id}/take-vacation/{days}", "e85ab6e5-7c41-45a5-8963-ec6158b6faad", -1)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}
