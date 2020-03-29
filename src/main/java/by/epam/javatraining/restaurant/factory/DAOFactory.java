package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.OrderDAO;
import by.epam.javatraining.restaurant.dao.PositionDAO;
import by.epam.javatraining.restaurant.dao.UserDAO;

public interface DAOFactory {

    UserDAO getUserDAO();

    OrderDAO getOrderDAO();

    PositionDAO getPositionDAO();
}
