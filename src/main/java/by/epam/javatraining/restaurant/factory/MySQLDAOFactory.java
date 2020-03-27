package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.order.MySQLOrderDAO;
import by.epam.javatraining.restaurant.dao.order.OrderDAO;
import by.epam.javatraining.restaurant.dao.user.MySQLUserDAO;
import by.epam.javatraining.restaurant.dao.user.UserDAO;

public enum MySQLDAOFactory implements DAOFactory {

    INSTANCE;

    private final UserDAO mySQLUserDAO = new MySQLUserDAO();
    private final OrderDAO mySQLOrderDAO = new MySQLOrderDAO();

    @Override
    public UserDAO getUserDAO() {
        return mySQLUserDAO;
    }

    @Override
    public OrderDAO getOrderDAO() {
        return mySQLOrderDAO;
    }
}
