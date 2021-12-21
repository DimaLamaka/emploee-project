package by.lamaka.application.service;

import by.lamaka.application.entity.Employee;

import java.util.Map;

public interface MapperService {
    Employee map(Map<String, String> empParams);
}
