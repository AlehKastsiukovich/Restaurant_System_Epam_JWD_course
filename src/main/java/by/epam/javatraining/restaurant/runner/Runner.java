package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restaurant.dao.ItemOrderDAO;
import by.epam.javatraining.restaurant.dao.impl.ItemOrderDAOImpl;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.factory.DAOFactory;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private String name;
    private int age;

    public Runner(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) throws SQLException, DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
//        ItemOrderDAO dao = ItemOrderDAOImpl.getInstance();
//        Position position = new Position();
//        ItemOrder itemOrder = new ItemOrder();
//        Order order = new Order();
//        order.setOrderId(50);
//        itemOrder.setPosition(position);
//        itemOrder.setQuantity(4);
//        itemOrder.setOrder(order);

//        DeliveryAddress address = new DeliveryAddress();
//        address.setStreet("Street3");
//        address.setBuildNumber(3);
//        address.setApartmentNumber(33);
//
//        DeliveryAddressDAO dao = DAOFactoryImpl.INSTANCE.getDeliveryAddressDAO();
//
//        dao.create(address);
//        Connection connection = pool.getConnection();
//        Statement statement = connection.createStatement();
//
//        ResultSet set = statement.executeQuery( Statement.RETURN_GENERATED_KEYS);
//
//        if (set.next()) {
//            int a = set.getInt(1);
//            System.out.println(a);
//        }
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }

        Runner runner = (Runner) object;
        return runner.name.equals(this.name) && runner.age == this.age;
    }
}
