package by.lamaka.application.service.impl;


import by.lamaka.application.entity.Employee;
import by.lamaka.application.service.FileParserService;
import by.lamaka.application.service.mappers.MapperService;
import by.lamaka.application.service.mappers.impl.FileMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileParserServiceImpl implements FileParserService {
    MapperService<String[]> mapperService;

    public FileParserServiceImpl() {
        mapperService = new FileMapper();
    }

    @Override
    public List<Employee> getListEmployeeFromFile(String path) throws IOException, SQLException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.ready()) {
                String nextLine = bufferedReader.readLine();
                String[] split = nextLine.split("\\s*;\\s*");
                Employee employee = mapperService.map(split);
                employees.add(employee);
            }
        }
        return employees;
    }
}
