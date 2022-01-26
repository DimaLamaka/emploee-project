package by.lamaka.application.service;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService {
    String getUserInput(String message) throws IOException;
    Employee getEmployeeFromUserInput() throws IOException, ValidateException, SQLException;
    void closeReader() throws IOException;
}
