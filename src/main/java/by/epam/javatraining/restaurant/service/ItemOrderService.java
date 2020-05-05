package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;

import java.util.Map;

public interface ItemOrderService {

    void createItemOrder(Map<Position, Integer> positionIntegerMap , Order order) throws ServiceException;


}
