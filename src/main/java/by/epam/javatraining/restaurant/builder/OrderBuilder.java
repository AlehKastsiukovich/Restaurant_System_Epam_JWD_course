package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.DeliveryAddressService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.Date;

public class OrderBuilder {
    private static final Logger LOGGER = LogManager.getLogger(OrderBuilder.class);

    private int orderId;
    private int customerId;
    private int orderStatusId;
    private Date orderDate;
    private DeliveryAddress deliveryAddress;
    private BigDecimal totalPrice;

    private DeliveryAddressService service = ServiceFactory.INSTANCE.getDeliveryAddressService();

    public OrderBuilder() {
        deliveryAddress = new DeliveryAddress();
        orderDate = new Date();
        orderStatusId = 1;
    }

    public OrderBuilder buildOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderBuilder buildCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder buildOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
        return this;
    }

    public OrderBuilder buildOrderDate(Date date) {
        this.orderDate = date;
        return this;
    }

    public OrderBuilder buildTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public OrderBuilder buildDeliveryAddress(int addressId) {
        try {
            deliveryAddress = service.readDeliveryAddressById(addressId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return this;
    }

    public Order build() {
        return new Order(this);
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
