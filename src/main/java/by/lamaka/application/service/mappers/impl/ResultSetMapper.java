package by.lamaka.application.service.mappers.impl;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.mappers.MapperService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper implements MapperService<ResultSet> {
    @Override
    public Employee map(ResultSet resultSet) throws SQLException {
        return Employee.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .currentTask(resultSet.getString("current_task"))
                .isWork(resultSet.getBoolean("is_work"))
                .build();
    }
}
