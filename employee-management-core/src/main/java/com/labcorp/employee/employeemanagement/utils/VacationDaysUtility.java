package com.labcorp.employee.employeemanagement.utils;

import com.labcorp.employee.employeemanagement.constants.EmployeeConstant;
import com.labcorp.employee.employeemanagement.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class VacationDaysUtility {

    public void work(int daysWorked, Employee employee) {
        float vacationDaysAccumulated = employee.getVacationDaysAccumulated(),
                maxVacationDaysInWorkYear = employee.getMaxVacationDaysInWorkYear();
        int daysWorkedCurrent = employee.getDaysWorked();

        if (daysWorked < 0)
            throw new IllegalArgumentException("Days worked should be a positive number");
        if (daysWorkedCurrent + daysWorked <= EmployeeConstant.DAYS_IN_WORK_YEAR) {
            float vacationDayGenerated =  ((daysWorked / (float) EmployeeConstant.DAYS_IN_WORK_YEAR) * maxVacationDaysInWorkYear);
            vacationDaysAccumulated += vacationDayGenerated;
            daysWorkedCurrent += daysWorked;
            if (vacationDaysAccumulated > maxVacationDaysInWorkYear)
                vacationDaysAccumulated = maxVacationDaysInWorkYear;
            employee.setVacationDaysAccumulated(vacationDaysAccumulated);
            employee.setDaysWorked(daysWorkedCurrent);
        } else {
            throw new IllegalArgumentException("Cannot work for more than " + EmployeeConstant.DAYS_IN_WORK_YEAR + " days in a work year");
        }
    }

    public void takeVacation(float vacationDays, Employee employee) {
        float vacationDaysAccumulated = employee.getVacationDaysAccumulated();
        if (vacationDays < 0)
            throw new IllegalArgumentException("Vacation days should be a positive number");

        if (vacationDays <= vacationDaysAccumulated) {
            vacationDaysAccumulated -= vacationDays;
            employee.setVacationDaysAccumulated(vacationDaysAccumulated);
        } else {
            throw new IllegalArgumentException("Cannot take more vacation than available vacation days " + vacationDaysAccumulated);
        }
    }

}
