package by.lamaka.application.service.impl;

import by.lamaka.application.exceptions.ValidateException;
import by.lamaka.application.service.ValidateService;

import java.util.Map;

public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean validateId(String id) throws ValidateException {
        if (!id.matches("[\\d]+")) {
            throw new ValidateException("Id = " + id + " is not correct");
        }
        return true;
    }

    @Override
    public boolean validateUserInputEmployee(Map<String, String> params) throws ValidateException {
        String name = params.get("name");
        if (name == null || "".equals(name)) {
            throw new ValidateException("Name can't be empty or null");
        }
        String currentWork = params.get("task");
        if (currentWork == null || "".equals(currentWork)) {
            throw new ValidateException("Name can't be empty or null");
        }
        String isWork = params.get("is work");
        if (!isWork.equals("true") && !isWork.equals("false")) {
            throw new ValidateException("Is work can be only true or false");
        }
        return true;
    }
}
