package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restaurant.dao.impl.DeliveryAddressDAOImpl;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.factory.DAOFactory;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

//        DeliveryAddress address = new DeliveryAddress();
//        address.setDeliveryAddressId(15);
//        address.setStreet("Street2");
//        address.setApartmentNumber(3);
//        address.setBuildNumber(4);
        List<DeliveryAddress> list = new ArrayList<>();
        DeliveryAddressDAO dao = DAOFactoryImpl.INSTANCE.getDeliveryAddressDAO();
        DeliveryAddress address = null;
        try {
            list = dao.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        for (DeliveryAddress deliveryAddress : list) {
            System.out.println(deliveryAddress);
        }

    }
}
