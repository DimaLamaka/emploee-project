package by.lamaka.application.service.impl;

import by.lamaka.application.entity.CacheServer;
import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.EmployeeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeCacheService implements EmployeeService {
    EmployeeService employeeService;

    public EmployeeCacheService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException, ValidateException {
        employeeService.addEmployee(employee);
        CacheServer.getCache().put(employee.getId(), employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException, ValidateException {
        employeeService.updateEmployee(employee);
        CacheServer.getCache().put(employee.getId(), employee);
    }

    @Override
    public void addListEmployee(List<Employee> employees) throws SQLException, ValidateException {
        employeeService.addListEmployee(employees);
        employees.forEach(emp -> CacheServer.getCache().put(emp.getId(), emp));
    }

    @Override
    public Employee getEmployeeById(String id) throws SQLException, ValidateException {
        Employee employee = CacheServer.getCache().get(Long.parseLong(id));
        if (employee != null) {
            return employee;
        }
        employee = employeeService.getEmployeeById(id);
        return employee;
    }

    @Override
    public List<Employee> getListEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>(CacheServer.getCache()
                .values());
        if (!employees.isEmpty()) {
            return employees;
        }
        employees = employeeService.getListEmployee();
        return employees;
    }
}
