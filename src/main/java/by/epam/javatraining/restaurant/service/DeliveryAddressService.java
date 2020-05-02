package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import java.util.List;

public interface DeliveryAddressService {

    void createDeliveryAddress();

    void updateDeliveryAddress(DeliveryAddress deliveryAddress);

    void deleteDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress readDeliveryAddressById(int id);

    List<DeliveryAddress> getAllAddresses();
}
