package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.PositionBuilder;
import by.epam.javatraining.restaurant.builder.UserBuilder;
import by.epam.javatraining.restaurant.dao.PositionDAO;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.validator.UserValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Runner {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        User user = new UserBuilder()
                .buildLogin("aleh15")
                .buildPassword("parol15")
                .buildEmail("myemail14@mail.ru")
                .buildPhoneNumber("+375292999615")
                .build();

        UserService service = ServiceFactory.INSTANCE.getUserService();

        try {
            service.registerUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
