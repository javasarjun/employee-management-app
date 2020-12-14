package com.labcorp.employee.employeemanagement.utils;


import com.labcorp.employee.employeemanagement.constants.EmployeeConstant;
import com.labcorp.employee.employeemanagement.model.Employee;
import com.labcorp.employee.employeemanagement.model.HourlyEmployee;
import com.labcorp.employee.employeemanagement.model.Manager;
import com.labcorp.employee.employeemanagement.model.SalariedEmployee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
 class VacationDaysUtilityTest {

    Employee hourly;
    Employee salary;
    Employee manager;

   VacationDaysUtility vacationDaysUtility;


    @BeforeEach
    public void before() {
        hourly = new HourlyEmployee("HE 1", UUID.randomUUID());
        salary = new SalariedEmployee("SE 1", UUID.randomUUID());
        manager = new Manager("MA 1", UUID.randomUUID());
       vacationDaysUtility = new VacationDaysUtility();
    }


    @Test
     void workTestCannotWorkMoreDaysThanInWorkYear() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> vacationDaysUtility.work(261, hourly));
    }

    @Test
     void workTestDaysWorkedCannotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> vacationDaysUtility.work(-1, hourly));
    }

    @Test
     void workTestSuccess() {
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, hourly);
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, salary);
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, manager);
        assertAll(
                () -> assertEquals(EmployeeConstant.MAX_VACATION_DAYS_IN_HOURLY, hourly.getVacationDaysAccumulated()),
                () -> assertEquals(EmployeeConstant.MAX_VACATION_DAYS_IN_SALARIED, salary.getVacationDaysAccumulated()),
                () -> assertEquals(EmployeeConstant.MAX_VACATION_DAYS_IN_YEAR_MANAGER, manager.getVacationDaysAccumulated())
        );
    }

    @Test
     void workTestSuccessRandomDaysWorked() {
       vacationDaysUtility.work(200, hourly);
       vacationDaysUtility.work(200, salary);
       vacationDaysUtility.work(200, manager);

        assertAll(
                () -> assertEquals(7.692308f, hourly.getVacationDaysAccumulated()),
                () -> assertEquals(11.538462f, salary.getVacationDaysAccumulated()),
                () -> assertEquals(23.076923f, manager.getVacationDaysAccumulated())
        );
    }

    @Test
     void takeVacationTestVacationDaysCannotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> vacationDaysUtility.takeVacation(-1, hourly));
    }

    @Test
     void takeVacationTestCannotTakeMoreVacationThanAvailable() {
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, hourly);
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, salary);
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, manager);

        assertAll(
                () ->   Assertions.assertThrows(IllegalArgumentException.class, () ->vacationDaysUtility.takeVacation(11f, hourly)),
                () ->   Assertions.assertThrows(IllegalArgumentException.class, () -> vacationDaysUtility.takeVacation(16f, salary)),
                () ->   Assertions.assertThrows(IllegalArgumentException.class, () -> vacationDaysUtility.takeVacation(31f, manager))
        );

    }

    @Test
     void takeVacationTestSuccess() {
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, hourly);
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, salary);
       vacationDaysUtility.work(EmployeeConstant.DAYS_IN_WORK_YEAR, manager);
       vacationDaysUtility.takeVacation(EmployeeConstant.MAX_VACATION_DAYS_IN_HOURLY,hourly);
       vacationDaysUtility.takeVacation(EmployeeConstant.MAX_VACATION_DAYS_IN_SALARIED, salary);
       vacationDaysUtility.takeVacation(EmployeeConstant.MAX_VACATION_DAYS_IN_YEAR_MANAGER, manager);
        assertAll(
                () -> assertEquals(0f, hourly.getVacationDaysAccumulated()),

                () ->  assertEquals(0f, salary.getVacationDaysAccumulated()),

                () ->  assertEquals(0f, manager.getVacationDaysAccumulated())
        );

    }


}
