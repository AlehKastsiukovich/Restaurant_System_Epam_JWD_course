package main;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;
import by.epam.javatraining.restaurant.dao.ModelDAO;
import by.epam.javatraining.restaurant.dao.UserDAO;
import by.epam.javatraining.restaurant.entity.Role;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        ModelDAO<User, String> dao = new UserDAO();

        User user = new User();
        user.setUserId(10);
        user.setLogin("hellojava");
        user.setPassword("ppl");
        user.setEmail("freename@tut.by");
        user.setPhoneNumber("+102121212");
        Role role = new Role();
        role.setRoleId(1);
        user.setRole(role);

        try {
            dao.create(user);
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
