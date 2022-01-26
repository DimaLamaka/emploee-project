package by.lamaka.application.service.impl;


import by.lamaka.application.entity.CacheServer;
import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationServiceImpl implements ApplicationService {
    private static final String FILE_PATH = "src/main/resources/Employee.csv";
    private static final String END_APP = "EXIT";
    EmployeeInfoService employeeInfoService;
    FileParserService fileParserService;
    EmployeeService employeeService;
    UserService userService;
    ValidateService validateService;

    public ApplicationServiceImpl() {
        fileParserService = new FileParserServiceImpl();
        employeeService = new EmployeeCacheService(new EmployeeDBService());
        userService = new UserServiceImpl();
        validateService = new ValidateServiceImpl();
        employeeInfoService = new EmployeeInfoServiceImpl(employeeService);
    }

    @Override
    public void startApplication() {
        Thread thread = new Thread(employeeInfoService);
        thread.start();
        System.out.println("Start application...");
        try {
            employeeService.getListEmployee().forEach(emp -> CacheServer.getCache().put(emp.getId(), emp));
            List<Employee> employees = fileParserService.getListEmployeeFromFile(FILE_PATH);
            employeeService.addListEmployee(employees);
            System.out.println("Employees add to DB from file...");
        } catch (SQLException | IOException | ValidateException e) {
            System.err.println(e);
        }
        while (true) {
            try {
                String request = userService.getUserInput("Enter \"EXIT\" or Id employee..");
                if (request.equals(END_APP)) {
                    userService.closeReader();
                    return;
                }
                validateService.validateId(request);
                Employee employee = employeeService.getEmployeeById(request);

                if (employee != null) {
                    System.out.println(employee);
                    System.out.println("Update employee: ");
                    employee = userService.getEmployeeFromUserInput();
                    employee.setId(Integer.parseInt(request));
                    employeeService.updateEmployee(employee);
                    System.out.println("Employee was updated");
                } else {
                    System.out.println("Add new employee: ");
                    employee = userService.getEmployeeFromUserInput();
                    employeeService.addEmployee(employee);
                    System.out.println("Employee was added");
                }

            } catch (IOException | SQLException | ValidateException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
