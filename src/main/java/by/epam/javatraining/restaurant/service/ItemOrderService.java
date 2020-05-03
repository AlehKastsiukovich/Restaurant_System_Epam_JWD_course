package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.ServiceException;

public interface ItemOrderService {

    void createItemOrder(ItemOrder itemOrder, Order order) throws ServiceException;
}
