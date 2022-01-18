package by.lamaka.application.service;

import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface FileParserService {
    List<Employee> getListEmployeeFromFile(String path) throws IOException, ValidateException, SQLException;
}
