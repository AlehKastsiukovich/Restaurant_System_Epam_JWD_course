package by.epam.javatraining.restaurant.dao.impl;

import by.epam.javatraining.restaurant.builder.ItemOrderBuilder;
import by.epam.javatraining.restaurant.dao.DBFields;
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

    public String query = "select * from item_order where order_id = (?) and item_id = (?)";

    @Override
    public void create(ItemOrder itemOrder) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_ITEM_ORDER.getValue())) {

            statement.setInt(1, itemOrder.getPosition().getPositionId());
            statement.setInt(2, itemOrder.getQuantity());
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
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_ITEM_ORDER.getValue())) {

            statement.setInt(2, itemOrder.getOrder().getOrderId());
            statement.setInt(1, itemOrder.getPosition().getPositionId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public ItemOrder readByOrderIdAndPositionId(int orderId, int positionId) throws DAOException {
        ItemOrder itemOrder = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SQLQuery.READ_ITEM_ORDER_BY_ORDER_ID_AND_POSITION_ID.getValue());
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, orderId);
            statement.setInt(2, positionId);
            if (resultSet.next()) {
                itemOrder = buildItemOrder(resultSet);
            }

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
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_ITEM_ORDERS.getValue());
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

    @Override
    public void createItemOrderByOrder(ItemOrder itemOrder, Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_ITEM_ORDER_BY_ORDER.getValue())){

            statement.setInt(1, itemOrder.getPosition().getPositionId());
            statement.setInt(2, itemOrder.getQuantity());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setInt(4, order.getCustomerId());
            statement.setBigDecimal(5, order.getTotalPrice());
            statement.setInt(6, order.getOrderStatusId());
            statement.setString(7, order.getDeliveryAddress().getStreet());
            statement.setInt(8, order.getDeliveryAddress().getBuildNumber());
            statement.setInt(9, order.getDeliveryAddress().getApartmentNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    private ItemOrder buildItemOrder(ResultSet resultSet) throws SQLException {
        return new ItemOrderBuilder()
                .buildOrder(resultSet.getInt(DBFields.DB_ITEM_ORDER_ORDER_ID.getValue()))
                .buildPosition(resultSet.getInt(DBFields.DB_ITEM_ORDER_ITEM_ID.getValue()))
                .buildQuantity(resultSet.getInt(DBFields.DB_ITEM_ORDER_QUANTITY.getValue()))
                .build();
    }
}
