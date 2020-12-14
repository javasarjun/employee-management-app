package com.labcorp.employee.EmployeeManagement.model;


import com.labcorp.employee.EmployeeManagement.constants.EmployeeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
 class EmployeeTest {

    Employee hourly;
    Employee salary;
    Employee manager;


    @BeforeEach
    public void before() {
        hourly = new HourlyEmployee("HE 1", UUID.randomUUID());
        salary = new SalariedEmployee("SE 1", UUID.randomUUID());
        manager = new Manager("MA 1", UUID.randomUUID());
    }


    @Test
     void workTestCannotWorkMoreDaysThanInWorkYear() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> hourly.work(261));
    }

    @Test
     void workTestDaysWorkedCannotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> hourly.work(-1));
    }

    @Test
     void workTestSuccess() {
        hourly.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        salary.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        manager.work(EmployeeConstant.DAYS_IN_WORK_YEAR);

        assertAll(
                () -> assertEquals(EmployeeConstant.MAX_VACATION_DAYS_IN_HOURLY, hourly.getVacationDaysAccumulated()),
                () -> assertEquals(EmployeeConstant.MAX_VACATION_DAYS_IN_SALARIED, salary.getVacationDaysAccumulated()),
                () -> assertEquals(EmployeeConstant.MAX_VACATION_DAYS_IN_YEAR_MANAGER, manager.getVacationDaysAccumulated())
        );
    }

    @Test
     void workTestSuccessRandomDaysWorked() {
        hourly.work(200);
        salary.work(200);
        manager.work(200);

        assertAll(
                () -> assertEquals(7.692308f, hourly.getVacationDaysAccumulated()),
                () -> assertEquals(11.538462f, salary.getVacationDaysAccumulated()),
                () -> assertEquals(23.076923f, manager.getVacationDaysAccumulated())
        );
    }

    @Test
     void takeVacationTestVacationDaysCannotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> hourly.work(-1));
    }

    @Test
     void takeVacationTestCannotTakeMoreVacationThanAvailable() {
        hourly.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        salary.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        manager.work(EmployeeConstant.DAYS_IN_WORK_YEAR);

        assertAll(
                () ->   Assertions.assertThrows(IllegalArgumentException.class, () -> hourly.takeVacation(11f)),
                () ->   Assertions.assertThrows(IllegalArgumentException.class, () -> salary.takeVacation(16f)),
                () ->   Assertions.assertThrows(IllegalArgumentException.class, () -> manager.takeVacation(31f))
        );

    }

    @Test
     void takeVacationTestSuccess() {
        hourly.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        salary.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        manager.work(EmployeeConstant.DAYS_IN_WORK_YEAR);
        hourly.takeVacation(EmployeeConstant.MAX_VACATION_DAYS_IN_HOURLY);
        salary.takeVacation(EmployeeConstant.MAX_VACATION_DAYS_IN_SALARIED);
        manager.takeVacation(EmployeeConstant.MAX_VACATION_DAYS_IN_YEAR_MANAGER);
        assertAll(
                () -> assertEquals(0f, hourly.getVacationDaysAccumulated()),

                () ->  assertEquals(0f, salary.getVacationDaysAccumulated()),

                () ->  assertEquals(0f, manager.getVacationDaysAccumulated())
        );

    }


}
