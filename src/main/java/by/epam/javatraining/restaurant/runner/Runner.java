package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.dao.order.MySQLOrderDAO;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.pool.ConnectionPool;

import java.math.BigDecimal;
import java.util.Date;

public class Runner {

    public static void main(String[] args) {
        ConnectionPool.getInstance().initializeConnectionPool();

        Order order = new Order();
        order.setOrderId(4);
        order.setCustomerId(6);
        order.setOrderDate(new Date());
        order.setOrderStatusId(1);
        order.setTotalPrice(new BigDecimal(300));

        DeliveryAddress address = new DeliveryAddress();
        address.setStreet("The Street grow!");
        address.setBuildNumber(329);

        order.setDeliveryAddress(address);

        MySQLOrderDAO dao = new MySQLOrderDAO();
        try {
            dao.update(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
