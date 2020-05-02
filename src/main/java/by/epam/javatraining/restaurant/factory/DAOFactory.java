package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.dao.*;

public interface DAOFactory {

    UserDAO getUserDAO();

    OrderDAO getOrderDAO();

    PositionDAO getPositionDAO();

    ItemOrderDAO getItemOrderDAO();

    DeliveryAddressDAO getDeliveryAddressDAO();
}
