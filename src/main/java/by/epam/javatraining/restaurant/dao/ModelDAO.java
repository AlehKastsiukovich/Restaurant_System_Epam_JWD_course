package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

public interface ModelDAO <T, Key> {

    void create(T t) throws DAOException;

    T read(Key key) throws DAOException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;

    List<T> getAll() throws DAOException;
}
