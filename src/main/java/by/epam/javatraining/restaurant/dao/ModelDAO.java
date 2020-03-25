package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;

import java.util.List;

public interface ModelDAO <T> {

    void create(T t) throws DAOException, ServiceException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;

    T readById(int id) throws DAOException;

    List<T> getAll() throws DAOException;
}
