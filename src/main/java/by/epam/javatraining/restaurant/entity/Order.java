package by.epam.javatraining.restaurant.entity;

import by.epam.javatraining.restaurant.builder.OrderBuilder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    private int customerId;
    private int orderStatusId;
    private Date orderDate;
    private DeliveryAddress deliveryAddress;
    private BigDecimal totalPrice;
    private List<ItemOrder> itemOrderList;

    public Order() {
        itemOrderList = new LinkedList<>();
    }

    public Order(OrderBuilder builder) {
        this.orderId = builder.getOrderId();
        this.customerId = builder.getCustomerId();
        this.orderStatusId = builder.getOrderStatusId();
        this.orderDate = builder.getOrderDate();
        this.deliveryAddress = builder.getDeliveryAddress();
        this.totalPrice = builder.getTotalPrice();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + orderId;
        hash = 31 * hash + customerId;
        hash = 31 * hash + orderId;
        hash = 31 * hash + (orderDate == null ? 0 : orderDate.hashCode());
        hash = 31 * hash + (deliveryAddress == null ? 0 : deliveryAddress.hashCode());
        hash = 31 * hash + (totalPrice == null ? 0 : totalPrice.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return orderId == order.orderId &&
                customerId == order.customerId &&
                orderStatusId == order.orderStatusId &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public String toString() {
        return "Order[" +
                "orderId = " + orderId +
                ", customerId = " + customerId +
                ", orderStatusId = " + orderStatusId +
                ", orderDate = " + orderDate +
                ", DeliveryAddress = " + deliveryAddress +
                ", totalPrice = " + totalPrice +
                ']';
    }
}
