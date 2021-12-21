package by.lamaka.application.service;

import by.lamaka.application.service.impl.*;
import lombok.Getter;

@Getter
public class ServiceProvider {
    private final static ServiceProvider SERVICE_PROVIDER = new ServiceProvider();
    private final ApplicationService applicationService = new ApplicationServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final FileParserService fileParserService = new FileParserServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final ValidateService validateService = new ValidateServiceImpl();
    private final MapperService mapperService = new MapperServiceImpl();
    private final EmployeeInfoService infoService = new EmployeeInfoServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return SERVICE_PROVIDER;
    }

}
