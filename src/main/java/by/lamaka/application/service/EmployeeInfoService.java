package by.lamaka.application.service;


import java.sql.SQLException;

public interface EmployeeInfoService extends Runnable{
    void printAllEmployees() throws SQLException;
}
