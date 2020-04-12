package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.dao.OrderDAO;
import by.epam.javatraining.restaurant.dao.PositionDAO;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ItemOrderBuilder {
    private static final Logger LOGGER = LogManager.getLogger(ItemOrderBuilder.class);

    private PositionDAO positionDAO = DAOFactoryImpl.INSTANCE.getPositionDAO();
    private OrderDAO orderDAO = DAOFactoryImpl.INSTANCE.getOrderDAO();

    private Position position;
    private Order order;
    private int quantity;

    public ItemOrderBuilder buildPosition(int id) {
        try {
            this.position = positionDAO.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        return this;
    }

    public ItemOrderBuilder buildOrder(int id) {
        try {
            this.order = orderDAO.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        return this;
    }

    public ItemOrderBuilder buildQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemOrder build() {
        return new ItemOrder(this);
    }

    public Position getPosition() {
        return position;
    }

    public Order getOrder() {
        return order;
    }

    public int getQuantity() {
        return quantity;
    }
}
