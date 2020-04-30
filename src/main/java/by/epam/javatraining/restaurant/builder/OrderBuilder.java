package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.entity.Order;
import java.math.BigDecimal;
import java.util.Date;

public class OrderBuilder {
    private int orderId;
    private int customerId;
    private int orderStatusId;
    private Date orderDate;
    private DeliveryAddress deliveryAddress;
    private BigDecimal totalPrice;

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
