package by.lamaka.application.service.impl;

import by.lamaka.application.dao.ProviderDAO;
import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.EmployeeService;
import by.lamaka.application.service.ServiceProvider;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EmployeeServiceImpl implements EmployeeService {
    private static final ProviderDAO PROVIDER_DAO = ProviderDAO.getInstance();

    @Override
    public void addEmployee(Map<String, String> paramEmployees) throws SQLException, ValidateException {
        ServiceProvider.getInstance().getValidateService().validateEmployee(paramEmployees);
        Employee employee = ServiceProvider.getInstance().getMapperService().map(paramEmployees);
        PROVIDER_DAO.getEmployeeDAO().addEmployee(employee);
    }

    @Override
    public void updateEmployee(Map<String, String> paramEmployees) throws SQLException, ValidateException {
        ServiceProvider.getInstance().getValidateService().validateEmployee(paramEmployees);
        Employee employee = ServiceProvider.getInstance().getMapperService().map(paramEmployees);
        PROVIDER_DAO.getEmployeeDAO().updateEmployee(employee);
    }

    @Override
    public void addListEmployee(List<Map<String, String>> empParams) throws SQLException, ValidateException {
        List<Employee> employees = new ArrayList<>();
        if (!empParams.isEmpty()) {
            for (Map<String, String> map : empParams) {
                ServiceProvider.getInstance().getValidateService().validateEmployee(map);
                employees.add(ServiceProvider.getInstance().getMapperService().map(map));
            }
        }
        PROVIDER_DAO.getEmployeeDAO().addListEmployee(employees);
    }

    @Override
    public Employee getEmployeeById(String id) throws SQLException, ValidateException {
        ServiceProvider.getInstance().getValidateService().validateId(id);
        return PROVIDER_DAO.getEmployeeDAO().getEmployeeById(Long.parseLong(id));
    }

    @Override
    public List<Employee> getListEmployee() throws SQLException {
        return PROVIDER_DAO.getEmployeeDAO().getListEmployee();
    }
}
