package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;
import java.util.List;

public interface OrderService {

    void createOrder(Order order) throws ServiceException;

    void deleteOrder(Order order) throws ServiceException;

    Order getOrderById(int id) throws ServiceException;

    List<Order> getAllOrders() throws ServiceException;
}
