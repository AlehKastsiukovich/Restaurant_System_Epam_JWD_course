package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.PositionDAO;
import by.epam.javatraining.restaurant.dao.impl.OrderDAOImpl;
import by.epam.javatraining.restaurant.dao.OrderDAO;
import by.epam.javatraining.restaurant.dao.impl.PositionDAOImpl;
import by.epam.javatraining.restaurant.dao.impl.UserDAOImpl;
import by.epam.javatraining.restaurant.dao.UserDAO;

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
}
