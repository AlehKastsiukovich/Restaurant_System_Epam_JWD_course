package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.*;
import by.epam.javatraining.restaurant.dao.impl.*;

public enum DAOFactoryImpl implements DAOFactory {

    INSTANCE;

    @Override
    public UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return OrderDAOImpl.getInstance();
    }

    @Override
    public PositionDAO getPositionDAO() { return PositionDAOImpl.getInstance(); }

    @Override
    public ItemOrderDAO getItemOrderDAO() {
        return ItemOrderDAOImpl.getInstance();
    }

    @Override
    public DeliveryAddressDAO getDeliveryAddressDAO() { return DeliveryAddressDAOImpl.getInstance(); }
}
