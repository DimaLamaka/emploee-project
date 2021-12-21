package by.lamaka.application.service.impl;


import by.lamaka.application.service.FileParserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParserServiceImpl implements FileParserService {


    @Override
    public List<Map<String, String>> getListEmployeeParamsFromFile(String path) throws IOException {
        List<Map<String, String>> employeesParams = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.ready()) {
                String nextLine = bufferedReader.readLine();
                String[] split = nextLine.split("\\s*;\\s*");

                Map<String, String> empParams = new HashMap<>();
                empParams.put("id", split[0]);
                empParams.put("name", split[1]);
                empParams.put("task", split[2]);
                empParams.put("is work", split[3]);

                employeesParams.add(empParams);
            }
        }
        return employeesParams;
    }
}
