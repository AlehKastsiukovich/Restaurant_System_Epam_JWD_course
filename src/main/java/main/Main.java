package main;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;
import by.epam.javatraining.restaurant.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        Connection connection = pool.getConnection();

        String query = "select * from restaurant_user";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<User> list = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            list.add(user);
        }

        for (User user : list) {
            System.out.println(user.toString());
        }
    }
}
