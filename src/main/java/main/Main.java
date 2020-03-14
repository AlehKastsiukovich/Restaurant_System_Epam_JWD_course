package main;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;
import by.epam.javatraining.restaurant.dao.ModelDAO;
import by.epam.javatraining.restaurant.dao.UserDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        ModelDAO<User, String> dao = new UserDAO();

        User user = new User();
        user.setUserId(4);
        user.setLogin("kaffka3");
        user.setPassword("kaffka1236");

        try {
            dao.delete(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        List<User> list = null;
        try {
            list = dao.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        for (User user1 : list) {
            System.out.println(user1);
        }
    }
}
