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
        User user = null;
        try {
            user = dao.read("kent");
        } catch (DAOException e) {
            e.printStackTrace();
        }

        System.out.println(user.toString());
//        ConnectionPool pool = ConnectionPool.getInstance();
//        pool.initializeConnectionPool();

//
//        Connection connection = pool.getConnection();
//
//        String query = "select * from restaurant_user";
//        PreparedStatement statement = connection.prepareStatement(query);
//        ResultSet resultSet = statement.executeQuery();
//
//        List<User> list = new ArrayList<>();
//
//        while (resultSet.next()) {
//            User user = new User();
//            user.setUserId(resultSet.getInt("user_id"));
//            list.add(user);
//        }
//
//        for (User user : list) {
//            System.out.println(user.toString());
//        }
    }
}
