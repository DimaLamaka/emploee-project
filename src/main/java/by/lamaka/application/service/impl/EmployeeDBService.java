package by.lamaka.application.service.impl;

import by.lamaka.application.dao.EmployeeDAO;
import by.lamaka.application.dao.impl.EmployeeDAOImpl;
import by.lamaka.application.entity.Employee;
import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.EmployeeService;
import by.lamaka.application.service.ValidateService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


import java.sql.SQLException;
import java.util.List;

@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class EmployeeDBService implements EmployeeService {
    EmployeeDAO employeeDAO;
    ValidateService validateService;

    public EmployeeDBService() {
        employeeDAO = new EmployeeDAOImpl();
        validateService = new ValidateServiceImpl();
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException{
        if(employee!=null){
            employeeDAO.addEmployee(employee);
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException, ValidateException {
        if(employee!=null){
            employeeDAO.updateEmployee(employee);
        }
    }

    @Override
    public void addListEmployee(List<Employee> employees) throws SQLException {
        if(!employees.isEmpty()){
            employeeDAO.addListEmployee(employees);
        }
    }

    @Override
    public Employee getEmployeeById(String id) throws SQLException, ValidateException {
        validateService.validateId(id);
        return employeeDAO.getEmployeeById(Long.parseLong(id));
    }

    @Override
    public List<Employee> getListEmployee() throws SQLException {
        return employeeDAO.getListEmployee();
    }
}
