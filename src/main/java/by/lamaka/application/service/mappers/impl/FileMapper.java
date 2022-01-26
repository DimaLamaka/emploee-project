package by.lamaka.application.service.mappers.impl;


import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.mappers.MapperService;

public class FileMapper implements MapperService<String[]> {
    @Override
    public Employee map(String[] strings) {
        return Employee.builder()
                .id(Long.parseLong(strings[0]))
                .name(strings[1])
                .currentTask(strings[2])
                .isWork(Boolean.parseBoolean(strings[3]))
                .build();
    }
}
