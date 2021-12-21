package by.lamaka.application.service.impl;

import by.lamaka.application.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final BufferedReader bufferedReader;

    public UserServiceImpl() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getUserInput(String message) throws IOException {
        System.out.println(message);
        return bufferedReader.readLine();
    }

    @Override
    public Map<String, String> getEmployeeParams() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("name", getUserInput("Input name: "));
        map.put("task", getUserInput("Input current task: "));
        map.put("is work", getUserInput("Input is work (true or false) : "));
        return map;
    }


    public void closeReader() throws IOException {
        bufferedReader.close();
    }
}
