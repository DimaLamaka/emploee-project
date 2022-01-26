package by.lamaka.application.runner;


import by.lamaka.application.service.ApplicationService;
import by.lamaka.application.service.impl.ApplicationServiceImpl;

public class Runner {
    public static void main(String[] args) {
        ApplicationService applicationService = new ApplicationServiceImpl();
        applicationService.startApplication();
    }
}
