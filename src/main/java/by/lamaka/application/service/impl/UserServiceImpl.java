package by.lamaka.application.service.impl;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.UserService;
import by.lamaka.application.service.ValidateService;
import by.lamaka.application.service.mappers.MapperService;
import by.lamaka.application.service.mappers.impl.UserInputMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    ValidateService validateService;
    BufferedReader bufferedReader;
    MapperService<Map<String,String>> mapperService;

    public UserServiceImpl() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        validateService = new ValidateServiceImpl();
        mapperService = new UserInputMapper();
    }

    @Override
    public String getUserInput(String message) throws IOException {
        System.out.println(message);
        return bufferedReader.readLine();
    }

    @Override
    public Employee getEmployeeFromUserInput() throws IOException, ValidateException, SQLException {
        Map<String, String> map = new HashMap<>();
        map.put("name", getUserInput("Input name: "));
        map.put("task", getUserInput("Input current task: "));
        map.put("is work", getUserInput("Input is work (true or false) : "));
        validateService.validateUserInputEmployee(map);
        return mapperService.map(map);
    }


    public void closeReader() throws IOException {
        bufferedReader.close();
    }
}
