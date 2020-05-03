package by.epam.javatraining.restaurant.service.impl;

import by.epam.javatraining.restaurant.dao.impl.ItemOrderDAOImpl;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.service.ItemOrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ItemOrderServiceImpl implements ItemOrderService {
    private static final Logger LOGGER = LogManager.getLogger(ItemOrderServiceImpl.class);

    private ItemOrderDAOImpl dao = ItemOrderDAOImpl.getInstance();

    private ItemOrderServiceImpl() {
    }

    private static class ItemOrderServiceImplHolder {
        private static final ItemOrderServiceImpl INSTANCE = new ItemOrderServiceImpl();
    }

    public static ItemOrderServiceImpl getInstance() {
        return ItemOrderServiceImplHolder.INSTANCE;
    }

    @Override
    public void createItemOrder(ItemOrder itemOrder, Order order) throws ServiceException {
        try {
            dao.createItemOrder(itemOrder, order);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
