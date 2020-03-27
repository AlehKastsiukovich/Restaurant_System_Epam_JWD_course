package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.order.OrderDAO;
import by.epam.javatraining.restaurant.dao.user.UserDAO;

public interface DAOFactory {

    UserDAO getUserDAO();

    OrderDAO getOrderDAO();
}
