package by.epam.javatraining.restaurant.dao.order;

import by.epam.javatraining.restaurant.builder.OrderBuilder;
import by.epam.javatraining.restaurant.dao.DBFields;
import by.epam.javatraining.restaurant.dao.query.SQLQuery;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(MySQLOrderDAO.class);

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_ORDER.getValue())) {

            createAddress(order);

            statement.setDate(1, new Date(new java.util.Date().getTime()));
            statement.setInt(2, order.getCustomerId());
            statement.setBigDecimal(3, order.getTotalPrice());
            statement.setInt(4, order.getOrderStatusId());
            statement.setString(5, order.getDeliveryAddress().getStreet());
            statement.setInt(6, order.getDeliveryAddress().getBuildNumber());
            statement.setInt(7, order.getDeliveryAddress().getApartmentNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_ORDER.getValue())) {

            statement.setBigDecimal(1, order.getTotalPrice());
            statement.setInt(2, order.getOrderStatusId());
            statement.setInt(3, order.getOrderId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_ORDER.getValue())) {

            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getCustomerId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public Order readById(int id) throws DAOException {
        Order order = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_ORDER_BY_ID.getValue())) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order = buildOrder(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return order;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_ORDERS.getValue());
             ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                Order order = buildOrder(resultSet);
                orderList.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return orderList;
    }

    private void createAddress(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_ADDRESS.getValue())) {

            statement.setString(1, order.getDeliveryAddress().getStreet());
            statement.setInt(2, order.getDeliveryAddress().getBuildNumber());
            statement.setInt(3, order.getDeliveryAddress().getApartmentNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        return new OrderBuilder()
                .buildOrderId(resultSet.getInt(DBFields.DB_ORDER_ID.getValue()))
                .buildOrderDate(resultSet.getDate(DBFields.DB_ORDER_DATE.getValue()))
                .buildCustomerId(resultSet.getInt(DBFields.DB_ORDER_CUSTOMER_ID.getValue()))
                .buildTotalPrice(resultSet.getBigDecimal(DBFields.DB_ORDER_TOTAL_PRICE.getValue()))
                .buildOrderStatusId(resultSet.getInt(DBFields.DB_ORDER_STATUS.getValue()))
                .build();
    }
}
