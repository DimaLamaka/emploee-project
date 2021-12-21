package by.lamaka.application.service;


import by.lamaka.application.exceptions.ValidateException;

import java.util.Map;

public interface ValidateService {
    boolean validateId(String id) throws ValidateException;
    boolean validateEmployee(Map<String,String> params) throws ValidateException;
}
