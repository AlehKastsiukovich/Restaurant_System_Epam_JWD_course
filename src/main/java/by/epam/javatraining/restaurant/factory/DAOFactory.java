package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.MySQLUserDAO;
import by.epam.javatraining.restaurant.dao.UserDAO;

public enum DAOFactory {

    INSTANCE;

    private UserDAO dao = new MySQLUserDAO();

    public UserDAO getUserDao() {
        return dao;
    }
}
