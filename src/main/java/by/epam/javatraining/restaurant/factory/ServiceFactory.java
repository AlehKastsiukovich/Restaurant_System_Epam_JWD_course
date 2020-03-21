package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.service.UserServiceImpl;

public enum ServiceFactory {

    INSTANCE;

    ServiceFactory() {
    }

    private final UserService service = new UserServiceImpl();

    public UserService getService() {
        return service;
    }
}
