package by.epam.javatraining.restaurant.entity;

import java.util.Date;
import java.util.Objects;

public class Bill {
    private int billId;
    private Order order;
    private User admin;
    private Date billDate;
    private PaymentType paymentType;

    public Bill() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + billId;
        hash = 31 * hash + (order == null ? 0 : order.hashCode());
        hash = 31 * hash + (admin == null ? 0 : admin.hashCode());
        hash = 31 * hash + (billDate == null ? 0 : billDate.hashCode());
        hash = 31 * hash + (paymentType == null ? 0 : paymentType.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Bill bill = (Bill) object;
        return billId == bill.billId &&
                Objects.equals(order, bill.order) &&
                Objects.equals(admin, bill.admin) &&
                Objects.equals(billDate, bill.billDate) &&
                paymentType == bill.paymentType;
    }

    @Override
    public String toString() {
        return "Bill[" +
                "id =" + billId +
                ", orderId = " + order.getOrderId() +
                ", admin = " + admin.getUserId() +
                ", date = " + billDate +
                ", paymentType = " + paymentType.getValue() +
                ']';
    }
}
