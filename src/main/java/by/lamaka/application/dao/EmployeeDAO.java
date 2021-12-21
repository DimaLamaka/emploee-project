package by.lamaka.application.dao;

import by.lamaka.application.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;

    void addListEmployee(List<Employee> employees) throws SQLException;

    Employee getEmployeeById(long id) throws SQLException;

    List<Employee> getListEmployee() throws SQLException;

}
