package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.ItemOrderBuilder;
import by.epam.javatraining.restaurant.dao.ItemOrderDAO;
import by.epam.javatraining.restaurant.dao.impl.ItemOrderDAOImpl;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.pool.ConnectionPool;

public class Runner {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        ItemOrder order = new ItemOrderBuilder()
                .buildOrder(3)
                .buildPosition(10)
                .buildQuantity(5)
                .build();

        System.out.println(order);

        ItemOrderDAO dao = ItemOrderDAOImpl.getInstance();
        try {
            dao.update(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
