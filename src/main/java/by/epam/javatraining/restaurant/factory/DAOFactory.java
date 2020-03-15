package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.UserDAO;

public interface DAOFactory {

    UserDAO getUserDAO();
}
