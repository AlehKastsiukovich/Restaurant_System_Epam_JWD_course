package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

public interface OrderDAO extends ModelDAO<Order> {

    @Override
    void create(Order order) throws DAOException;

    @Override
    void update(Order order) throws DAOException;

    @Override
    void delete(Order order) throws DAOException;

    @Override
    Order readById(int id) throws DAOException;

    @Override
    List<Order> getAll() throws DAOException;
}

