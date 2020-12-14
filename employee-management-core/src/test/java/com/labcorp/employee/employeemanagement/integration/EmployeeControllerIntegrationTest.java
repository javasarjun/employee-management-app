package com.labcorp.employee.employeemanagement.integration;

import com.labcorp.employee.employeemanagement.model.Employee;
import com.labcorp.employee.employeemanagement.model.EmployeeType;
import com.labcorp.employee.employeemanagement.utils.DatabaseUtility;
import com.labcorp.employee.employeemanagement.utils.VacationDaysUtility;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;



@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VacationDaysUtility vacationDaysUtility;

    @Autowired
    private DatabaseUtility databaseUtility;



    @Test
    @Order(1)
    public void testGetAllEmployees() {

        webTestClient.get().uri("/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Employee.class);
    }

    @Test
    @Order(2)
    public void testSetWorkDays() {
        String id = databaseUtility.getAllEmployees().get()
                .stream().filter(employee -> employee.getType().equals(EmployeeType.MANAGER))
                .findFirst().get().getId().toString();

        webTestClient.put().uri("/employee/{id}/days-worked/{days}", id, 200)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.vacationDaysAccumulated")
                .isEqualTo(23.076923f);
    }

    @Test
    @Order(3)
    public void testSetVacationDays() {
        Employee hourly = databaseUtility.getAllEmployees().get()
                .stream().filter(employee -> employee.getType().equals(EmployeeType.HOURLY))
                .findFirst().get();
        vacationDaysUtility.work(200, hourly);
        webTestClient.put().uri("/employee/{name}/take-vacation/{days}", hourly.getId(), 1.0f)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.vacationDaysAccumulated")
                .isEqualTo(6.692308f);
    }

}
