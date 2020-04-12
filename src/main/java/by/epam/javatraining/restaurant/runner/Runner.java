package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.ItemOrderBuilder;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.pool.ConnectionPool;

public class Runner {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        ItemOrder order = new ItemOrderBuilder()
                .buildOrder(3)
                .buildPosition(5)
                .buildQuantity(2)
                .build();

        System.out.println(order);
    }
}
