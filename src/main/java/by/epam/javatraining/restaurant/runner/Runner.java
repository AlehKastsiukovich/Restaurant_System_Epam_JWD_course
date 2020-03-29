package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.PositionBuilder;
import by.epam.javatraining.restaurant.dao.PositionDAO;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.pool.ConnectionPool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        PositionDAO dao = DAOFactoryImpl.INSTANCE.getPositionDAO();
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

//        Position position = new PositionBuilder()
//                .buildId(1)
//                .buildItemName("Sprite")
//                .buildItemPrice(new BigDecimal(2))
//                .buildPositionItemGroup(2)
//                .build();

        Position position = null;

        try {
            position = dao.readById(5);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        System.out.println(position.toString());
    }
}
