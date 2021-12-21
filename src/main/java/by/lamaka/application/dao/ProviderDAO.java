package by.lamaka.application.dao;

import by.lamaka.application.dao.impl.EmployeeDAOImpl;
import lombok.Getter;

@Getter
public class ProviderDAO {
    private static final ProviderDAO PROVIDER_DAO = new ProviderDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    private ProviderDAO() {
    }

    public static ProviderDAO getInstance() {
        return PROVIDER_DAO;
    }
}
