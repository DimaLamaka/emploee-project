package by.lamaka.application.service.mappers;

import by.lamaka.application.entity.Employee;

import java.sql.SQLException;


public interface MapperService<T> {
    Employee map(T t) throws SQLException;
}
