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
        order.setCustomerId(4);
        order.setOrderDate(new Date());
        order.setOrderStatusId(1);
        order.setTotalPrice(new BigDecimal(500));

        DeliveryAddress address = new DeliveryAddress();
        address.setStreet("The Street");
        address.setBuildNumber(1000);

        order.setDeliveryAddress(address);

        MySQLOrderDAO dao = new MySQLOrderDAO();
        try {
            dao.create(order);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
