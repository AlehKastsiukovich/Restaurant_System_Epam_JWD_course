package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.service.DeliveryAddressService;
import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.service.PositionService;
import by.epam.javatraining.restaurant.service.impl.DeliveryAddressServiceImpl;
import by.epam.javatraining.restaurant.service.impl.OrderServiceImpl;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.service.impl.PositionServiceImpl;
import by.epam.javatraining.restaurant.service.impl.UserServiceImpl;

public enum ServiceFactory {

    INSTANCE;

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public OrderService getOrderService() {
        return OrderServiceImpl.getInstance();
    }

    public PositionService getPositionService() { return PositionServiceImpl.getInstance(); }

    public DeliveryAddressService getDeliveryAddressService() { return DeliveryAddressServiceImpl.getInstance(); }
}
