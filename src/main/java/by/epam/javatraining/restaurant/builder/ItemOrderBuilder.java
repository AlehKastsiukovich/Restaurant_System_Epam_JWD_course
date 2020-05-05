package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.service.PositionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ItemOrderBuilder {
    private static final Logger LOGGER = LogManager.getLogger(ItemOrderBuilder.class);

    private PositionService positionService = ServiceFactory.INSTANCE.getPositionService();
    private OrderService orderService = ServiceFactory.INSTANCE.getOrderService();

    private Position position;
    private Order order;
    private int quantity;

    public ItemOrderBuilder buildPosition(int id) {
        try {
            position = positionService.getPositionById(id);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return this;
    }

    public ItemOrderBuilder buildOrder(int orderId) {
        try {
            order = orderService.getOrderById(orderId);
        } catch (ServiceException e) {
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
