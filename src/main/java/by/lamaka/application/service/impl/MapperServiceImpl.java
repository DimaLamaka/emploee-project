package by.lamaka.application.service.impl;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.MapperService;

import java.util.Map;

public class MapperServiceImpl implements MapperService {

    @Override
    public Employee map(Map<String, String> empParams) {
        return Employee.builder()
                .id(Long.parseLong(empParams.get("id")))
                .name(empParams.get("name"))
                .currentTask(empParams.get("task"))
                .isWork(Boolean.parseBoolean(empParams.get("is work")))
                .build();
    }
}
