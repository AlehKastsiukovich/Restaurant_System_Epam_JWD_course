package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.builder.OrderBuilder;
import by.epam.javatraining.restaurant.dao.OrderDAO;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.factory.DAOFactory;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

public class Runner {

    public static void main(String[] args) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();

        Order order = new OrderBuilder()
                .buildOrderStatusId(1)
                .buildTotalPrice(new BigDecimal(100))
                .buildCustomerId(29)
                .build();

        OrderDAO dao = DAOFactoryImpl.INSTANCE.getOrderDAO();

        Connection connection = pool.getConnection();
        String statement = "insert into delivery_address (street, build, apartment) VALUES ('street', 5, 5)";
        PreparedStatement st = connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
        st.executeUpdate();

        ResultSet set = st.getGeneratedKeys();

        if (set.next()) {
            int value = set.getInt(1);
            System.out.println(value);
        }

    }
}
