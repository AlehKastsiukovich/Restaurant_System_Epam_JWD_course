package by.epam.javatraining.restaurant.dao.order;

import by.epam.javatraining.restaurant.dao.query.SQLQuery;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(MySQLOrderDAO.class);

    private static final String query = "update `order` set total_price = (?), order_status = (?)" +
            " where order_id = (?)";

    @Override
    public void create(Order order) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
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

    }

    @Override
    public Order readById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        return null;
    }

    private void createAddress(Order order) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
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
}
