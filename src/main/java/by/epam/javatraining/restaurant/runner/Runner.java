package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.UserBuilder;
import by.epam.javatraining.restaurant.dao.user.MySQLUserDAO;
import by.epam.javatraining.restaurant.dao.user.UserDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.DAOFactory;
import by.epam.javatraining.restaurant.factory.MySQLDAOFactory;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.validator.UserValidator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class Runner {

    public static void main(String[] args) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
//
//        UserService service = ServiceFactory.INSTANCE.getUserService();
//        User user = new UserBuilder()
//                .buildLogin("Name")
//                .buildPassword("kaffka123")
//                .buildPhoneNumber("+375292999615")
//                .buildEmail("hello228@gmail.com")
//                .buildFirstName("Ян")
//                .buildLastName("He-HE")
//                .build();
//
//        System.out.println(user.toString());
//
//        User user2 = null;
//
//        System.out.println(UserValidator.INSTANCE.validateUser(user));

        UserDAO dao = new MySQLUserDAO();
        User user = null;
        List<User> list = null;
        try {
            list =  dao.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        System.out.println(list.toString());
    }
}
