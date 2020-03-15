package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.user.MySQLUserDAO;
import by.epam.javatraining.restaurant.dao.user.UserDAO;

public enum MySQLDAOFactory implements DAOFactory{

    INSTANCE;

    private final UserDAO mySQLUserDAO = new MySQLUserDAO();

    @Override
    public UserDAO getUserDAO() {
        return mySQLUserDAO;
    }
}
