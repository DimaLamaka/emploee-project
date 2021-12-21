package by.lamaka.application.service;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    String getUserInput(String message) throws IOException;
    Map<String,String> getEmployeeParams() throws IOException, InterruptedException;
    void closeReader() throws IOException;
}
