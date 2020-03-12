package main;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;
import by.epam.javatraining.restaurant.entity.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        pool.initializeConnectionPool();

        User user = new User();
        User user2 = new User();

        user.setId(1);
        user.setLogin("kaffka");
        user.setPassword("pass");
        user.setEmail("3");

        user2.setId(1);
        user2.setLogin("kaffka");
        user2.setPassword("pass");
        user.setEmail("4");

        System.out.println(user2.toString());

//        String sql = "select * from restaurnt.restaurant_user_role";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
//
//        while (set.next()) {
//            System.out.println(set.getInt("role_id") + " " + set.getString("role_name"));
//        }



//        DriverManager.registerDriver(new Driver());
//
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost/restaurnt",
//                "root",
//                "root"
//        );
//
//        PreparedStatement statement = connection.prepareStatement(
//                "select * from restaurnt.restaurant_user_role"
//        );
//
//        ResultSet set = statement.executeQuery();
//
//        while (set.next()) {
//            System.out.println(set.getInt("role_id") + " " + set.getString("role_name"));
//        }


//        PreparedStatement preparedStatement2 = connection.prepareStatement(
//                "insert into restaurant_user_role (role_id, role_name) VALUE (1, 'cleaner')"
//        );

//        PreparedStatement preparedStatement = pool.getConnection().prepareStatement(
//                "insert into restaurant_user (user_id, login, password, email, phone_number, first_name, last_name, role_id)  " +
//                        "value (6, 'kent1','brokman1','olezh88fdf@gmail.com'," +
//                        "'+375292999615', 'Aleh', 'Kastsiukovich', 4)"
//        );

//        preparedStatement.execute();
        //String inert = "insert intto restaurant_user values (1,)

//        PreparedStatement statement = connection.prepareStatement("select * restaurant_user");
//
//        ResultSet set = statement.getResultSet();
//
//        while (set.next()) {
//            System.out.println(set.getInt(""));
//        }
    }
}
