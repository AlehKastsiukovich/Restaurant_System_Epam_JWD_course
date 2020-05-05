package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restaurant.dao.ItemOrderDAO;
import by.epam.javatraining.restaurant.dao.OrderDAO;
import by.epam.javatraining.restaurant.dao.UserDAO;
import by.epam.javatraining.restaurant.dao.impl.ItemOrderDAOImpl;
import by.epam.javatraining.restaurant.entity.*;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.DAOFactory;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.service.DeliveryAddressService;
import by.epam.javatraining.restaurant.service.ItemOrderService;
import by.epam.javatraining.restaurant.service.OrderService;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        DeliveryAddressService service1 = ServiceFactory.INSTANCE.getDeliveryAddressService();
        ItemOrderService service2 = ServiceFactory.INSTANCE.getItemOrderService();
        UserDAO dao1 = DAOFactoryImpl.INSTANCE.getUserDAO();

        ItemOrderDAO dao = DAOFactoryImpl.INSTANCE.getItemOrderDAO();
        try {
            List<ItemOrder> itemOrderList = dao.getAll();
            System.out.println(itemOrderList);
        } catch (DAOException e) {
            e.printStackTrace();
        }


//        try {
//            Order order = service.getOrderById(22);
//            DeliveryAddress deliveryAddress = service1.readDeliveryAddressById(10);
//            System.out.println(order);
//            System.out.println(deliveryAddress);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
    }
}
