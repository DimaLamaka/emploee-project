package by.lamaka.application.dao.impl;

import by.lamaka.application.config.ConnectionDB;
import by.lamaka.application.dao.EmployeeDAO;
import by.lamaka.application.entity.CacheServer;
import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.mappers.MapperService;
import by.lamaka.application.service.mappers.impl.ResultSetMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class EmployeeDAOImpl implements EmployeeDAO {
    MapperService<ResultSet> mapperService;

    public EmployeeDAOImpl() {
        mapperService = new ResultSetMapper();
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employee_table(name,current_task,is_work) VALUES (?,?,?)")) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getCurrentTask());
            preparedStatement.setString(3, String.valueOf(employee.isWork()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE employee_table SET name=?,current_task=?,is_work=? WHERE id=?")) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getCurrentTask());
            preparedStatement.setString(3, String.valueOf(employee.isWork()));
            preparedStatement.setLong(4, employee.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void addListEmployee(List<Employee> employees) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employee_table(name,current_task,is_work) VALUES (?,?,?)")) {

            for (Employee emp : employees) {
                preparedStatement.setString(1, emp.getName());
                preparedStatement.setString(2, emp.getCurrentTask());
                preparedStatement.setString(3, String.valueOf(emp.isWork()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    @Override
    public Employee getEmployeeById(long id) throws SQLException {
        Employee employee = null;
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM employee_table WHERE id=?")) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee = mapperService.map(resultSet);
            }
        }
        return employee;
    }

    @Override
    public List<Employee> getListEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM employee_table")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = mapperService.map(resultSet);
                employees.add(employee);
            }
        }
        return employees;
    }
}
