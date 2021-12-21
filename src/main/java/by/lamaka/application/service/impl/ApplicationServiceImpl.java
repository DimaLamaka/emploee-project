package by.lamaka.application.service.impl;


import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.ApplicationService;
import by.lamaka.application.service.ServiceProvider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ApplicationServiceImpl implements ApplicationService {
    private static final String FILE_PATH = "src/main/resources/Employee.csv";
    private static final String END_APP = "EXIT";

    @Override
    public void startApplication() {
        Thread thread = new Thread(ServiceProvider.getInstance().getInfoService());
        thread.start();
        ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
        System.out.println("Start application...");
        try {
            List<Map<String, String>> listEmpParams = SERVICE_PROVIDER.getFileParserService().getListEmployeeParamsFromFile(FILE_PATH);
            SERVICE_PROVIDER.getEmployeeService().addListEmployee(listEmpParams);
            System.out.println("Employees add to DB from file...");
        } catch (SQLException | IOException | ValidateException e) {
            System.err.println(e);
        }
        Map<String, String> paramEmployee = null;
        while (true) {
            try {
                String request = SERVICE_PROVIDER.getUserService().getUserInput("Enter \"EXIT\" or Id employee..");
                if (request.equals(END_APP)) {
                    SERVICE_PROVIDER.getUserService().closeReader();
                    return;
                }
                Employee employee = SERVICE_PROVIDER.getEmployeeService().getEmployeeById(request);
                if (employee != null) {
                    System.out.println(employee);
                    System.out.println("Update employee: ");
                    paramEmployee = SERVICE_PROVIDER.getUserService().getEmployeeParams();
                    paramEmployee.put("id", request);
                    SERVICE_PROVIDER.getEmployeeService().updateEmployee(paramEmployee);
                    System.out.println("Employee was updated");
                } else {
                    System.out.println("Add new employee: ");
                    paramEmployee = SERVICE_PROVIDER.getUserService().getEmployeeParams();
                    paramEmployee.put("id", request);
                    SERVICE_PROVIDER.getEmployeeService().addEmployee(paramEmployee);
                    System.out.println("Employee was added");
                }

            } catch (IOException | SQLException | ValidateException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
