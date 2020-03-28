package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.service.OrderServiceImpl;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.service.UserServiceImpl;

public enum ServiceFactory {

    INSTANCE;

    ServiceFactory() {
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public OrderService getOrderService() {
        return OrderServiceImpl.getInstance();
    }
}
