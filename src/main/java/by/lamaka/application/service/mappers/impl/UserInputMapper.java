package by.lamaka.application.service.mappers.impl;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.mappers.MapperService;

import java.util.Map;

public class UserInputMapper implements MapperService<Map<String, String>> {
    @Override
    public Employee map(Map<String, String> employeeParams) {
        return Employee.builder()
                .name(employeeParams.get("name"))
                .currentTask(employeeParams.get("task"))
                .isWork(Boolean.parseBoolean(employeeParams.get("is work")))
                .build();
    }
}

