package by.lamaka.application.service;

import by.lamaka.application.exceptions.ValidateException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileParserService {
    List<Map<String,String>> getListEmployeeParamsFromFile(String path) throws IOException, ValidateException;
}
