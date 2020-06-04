package by.epam.javatraining.restaurant.builder;

import by.epam.javatraining.restaurant.entity.Bill;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.User;
import java.util.Date;

public class BillBuilder {
    private int billId;
    private Order order;
    private User admin;
    private Date billDate;

    public BillBuilder() {
    }

    public BillBuilder buildId(int billId) {
        this.billId = billId;
        return this;
    }

    public BillBuilder buildOrder(Order order) {
        this.order = order;
        return this;
    }

    public BillBuilder buildUser(User user) {
        admin = user;
        return this;
    }

    public BillBuilder buildDate(Date billDate) {
        this.billDate = billDate;
        return this;
    }

    public Bill build() {
        return new Bill(this);
    }

    public int getBillId() {
        return billId;
    }

    public Order getOrder() {
        return order;
    }

    public User getAdmin() {
        return admin;
    }

    public Date getBillDate() {
        return billDate;
    }
}
