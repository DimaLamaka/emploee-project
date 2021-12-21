package by.lamaka.application.runner;

import by.lamaka.application.service.ServiceProvider;


public class Runner {
    public static void main(String[] args) {
        ServiceProvider.getInstance().getApplicationService().startApplication();
    }
}
