package by.lamaka.application.service;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee) throws SQLException, ValidateException;

    void updateEmployee(Employee employee) throws SQLException, ValidateException;

    void addListEmployee(List<Employee> employees) throws SQLException, ValidateException;

    Employee getEmployeeById(String id) throws SQLException, ValidateException;

    List<Employee> getListEmployee() throws SQLException;
}
