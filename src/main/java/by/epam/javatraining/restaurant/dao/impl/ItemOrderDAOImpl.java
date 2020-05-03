package by.epam.javatraining.restaurant.dao.impl;

import by.epam.javatraining.restaurant.dao.ItemOrderDAO;
import by.epam.javatraining.restaurant.dao.SQLQuery;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderDAOImpl implements ItemOrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(ItemOrderDAOImpl.class);

    private ConnectionPool pool = ConnectionPool.getInstance();

    private ItemOrderDAOImpl() {
    }

    private static class ItemOrderDAOImplHolder {
        private static final ItemOrderDAOImpl INSTANCE = new ItemOrderDAOImpl();
    }

    public static ItemOrderDAOImpl getInstance() {
        return ItemOrderDAOImplHolder.INSTANCE;
    }

    public String query = "insert into item_order (order_id, item_id, quantity) " +
            "values ((select order_id from `order` " +
            "where order_date = (?) and customer_id = (?) and total_price = (?) " +
            "and order_status = (?) and id_delivery_address = (?)), (?), (?))";

    @Override
    public void create(ItemOrder itemOrder) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(2, itemOrder.getPosition().getPositionId());
            statement.setInt(3, itemOrder.getQuantity());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(ItemOrder itemOrder) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, itemOrder.getPosition().getPositionId());
            statement.setInt(2, itemOrder.getQuantity());
            statement.setInt(3, itemOrder.getOrder().getOrderId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(ItemOrder itemOrder) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(2, itemOrder.getOrder().getOrderId());
            statement.setInt(1, itemOrder.getPosition().getPositionId());
            statement.execute();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public ItemOrder readById(int id) throws DAOException {
        ItemOrder itemOrder = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            itemOrder = buildItemOrder(resultSet);

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return itemOrder;
    }

    @Override
    public List<ItemOrder> getAll() throws DAOException {
        List<ItemOrder> itemOrderList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ItemOrder itemOrder = buildItemOrder(resultSet);
                itemOrderList.add(itemOrder);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return itemOrderList;
    }

    public void createItemOrder(ItemOrder itemOrder, Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, (Date) order.getOrderDate());
            statement.setInt(2, order.getCustomerId());
            statement.setBigDecimal(3, order.getTotalPrice());
            statement.setInt(4, order.getOrderStatusId());
            statement.setInt(5, order.getDeliveryAddress().getDeliveryAddressId());
            statement.setInt(6, itemOrder.getPosition().getPositionId());
            statement.setInt(7, itemOrder.getQuantity());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    private ItemOrder buildItemOrder(ResultSet resultSet) {
        return new ItemOrder();
    }
}
