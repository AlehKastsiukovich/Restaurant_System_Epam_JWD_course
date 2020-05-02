package by.epam.javatraining.restaurant.service.impl;

import by.epam.javatraining.restaurant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.service.DeliveryAddressService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private static final Logger LOGGER = LogManager.getLogger(DeliveryAddressServiceImpl.class);

    private DeliveryAddressDAO dao = DAOFactoryImpl.INSTANCE.getDeliveryAddressDAO();

    private DeliveryAddressServiceImpl() {
    }

    private static class DeliveryAddressServiceImplHolder {
        private static final DeliveryAddressServiceImpl INSTANCE = new DeliveryAddressServiceImpl();
    }

    public static DeliveryAddressServiceImpl getInstance() {
        return DeliveryAddressServiceImplHolder.INSTANCE;
    }

    @Override
    public void createDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException {
        try {
            dao.create(deliveryAddress);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException {
        try {
            dao.update(deliveryAddress);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException {
        try {
            dao.delete(deliveryAddress);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public DeliveryAddress readDeliveryAddressById(int id) throws ServiceException {
        DeliveryAddress deliveryAddress;

        try {
            deliveryAddress = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return deliveryAddress;
    }

    @Override
    public List<DeliveryAddress> getAllAddresses() throws ServiceException {
        List<DeliveryAddress> addressList;
        try {
            addressList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return addressList;
    }
}
