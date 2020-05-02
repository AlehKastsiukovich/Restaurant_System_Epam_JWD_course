package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.exception.ServiceException;

import java.util.List;

public interface DeliveryAddressService {

    void createDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException;

    void updateDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException;

    void deleteDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException;

    DeliveryAddress readDeliveryAddressById(int id) throws ServiceException;

    List<DeliveryAddress> getAllAddresses() throws ServiceException;
}
