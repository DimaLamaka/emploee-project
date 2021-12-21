package by.lamaka.application.dao.impl;

import by.lamaka.application.config.ConnectionDB;
import by.lamaka.application.dao.EmployeeDAO;
import by.lamaka.application.entity.CacheServer;
import by.lamaka.application.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionDB.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employee_table(id,name,current_task,is_work) VALUES (?,?,?,?)")) {
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getCurrentTask());
            preparedStatement.setString(4, String.valueOf(employee.isWork()));
            preparedStatement.executeUpdate();
        }
        CacheServer.getCache().put(employee.getId(), employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionDB.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE employee_table SET name=?,current_task=?,is_work=? WHERE id=?")) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getCurrentTask());
            preparedStatement.setString(3, String.valueOf(employee.isWork()));
            preparedStatement.setLong(4, employee.getId());
            preparedStatement.executeUpdate();
        }
        CacheServer.getCache().put(employee.getId(), employee);
    }

    @Override
    public void addListEmployee(List<Employee> employees) throws SQLException {
        try (Connection connection = ConnectionDB.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employee_table(id,name,current_task,is_work) VALUES (?,?,?,?)")) {

            for (Employee emp : employees) {
                preparedStatement.setLong(1, emp.getId());
                preparedStatement.setString(2, emp.getName());
                preparedStatement.setString(3, emp.getCurrentTask());
                preparedStatement.setString(4, String.valueOf(emp.isWork()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
        employees.forEach(emp -> CacheServer.getCache().put(emp.getId(), emp));
    }

    @Override
    public Employee getEmployeeById(long id) throws SQLException {
        Employee employee = CacheServer.getCache().get(id);
        if (employee != null) {
            return employee;
        }
        try (Connection connection = ConnectionDB.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM employee_table WHERE id=?")) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .currentTask(resultSet.getString("current_task"))
                        .isWork(resultSet.getBoolean("is_work"))
                        .build();
            }
        }
        return employee;
    }

    @Override
    public List<Employee> getListEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        CacheServer.getCache()
                .values()
                .stream()
                .forEach(employees::add);
        if (!employees.isEmpty()) {
            return employees;
        }

        try (Connection connection = ConnectionDB.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM employee_table")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .currentTask(resultSet.getString("current_task"))
                        .isWork(resultSet.getBoolean("is_work"))
                        .build();
                employees.add(employee);
            }
        }
        return employees;
    }
}
