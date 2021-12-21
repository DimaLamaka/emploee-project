package by.lamaka.application.service;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void addEmployee(Map<String,String> paramEmployees) throws SQLException, ValidateException;

    void updateEmployee(Map<String,String> paramEmployees) throws SQLException, ValidateException;

    void addListEmployee(List<Map<String, String>> empParams) throws SQLException, ValidateException;

    Employee getEmployeeById(String id) throws SQLException, ValidateException;

    List<Employee> getListEmployee() throws SQLException;
}
