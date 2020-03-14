package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

public interface ModelDAO <T, Key> {

    void create(T t);

    T read(Key key) throws DAOException;

    void update(T t);

    void delete(T t);

    List<T> getAll() throws DAOException;
}
