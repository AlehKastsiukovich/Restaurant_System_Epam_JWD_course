package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

public interface DeliveryAddressDAO extends ModelDAO<DeliveryAddress> {

    @Override
    void create(DeliveryAddress deliveryAddress) throws DAOException;

    @Override
    void update(DeliveryAddress deliveryAddress) throws DAOException;

    @Override
    void delete(DeliveryAddress deliveryAddress) throws DAOException;

    @Override
    DeliveryAddress readById(int id) throws DAOException;

    @Override
    List<DeliveryAddress> getAll() throws DAOException;
}
