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
import java.util.Locale;

public class Runner {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();

        System.out.println(locale);

        Locale locale1 = new Locale("be", "BY");
        Locale.setDefault(locale1);

        System.out.println(Locale.getDefault());
    }
}
