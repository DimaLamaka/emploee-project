package by.lamaka.application.service.impl;


import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.EmployeeInfoService;
import by.lamaka.application.service.EmployeeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.SQLException;
import java.util.List;

@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    EmployeeService employeeService;

    public EmployeeInfoServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                printAllEmployees();
            } catch (InterruptedException | SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void printAllEmployees() throws SQLException {
        List<Employee> employeeList = employeeService.getListEmployee();
        System.out.println("-------ALL EMPLOYEE INFO--------");
        employeeList.forEach(System.out::println);
        System.out.println("--------------------------------");
    }

}
