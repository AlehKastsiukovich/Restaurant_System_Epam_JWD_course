package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.service.*;
import by.epam.javatraining.restaurant.service.impl.*;

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

    public ItemOrderService getItemOrderService() {
        return ItemOrderServiceImpl.getInstance();
    }
}
