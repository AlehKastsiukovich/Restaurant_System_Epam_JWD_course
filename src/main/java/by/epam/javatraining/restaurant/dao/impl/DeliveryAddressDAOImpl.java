package by.epam.javatraining.restaurant.dao.impl;

import by.epam.javatraining.restaurant.dao.DBFields;
import by.epam.javatraining.restaurant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restaurant.dao.SQLQuery;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressDAOImpl implements DeliveryAddressDAO {
    private static final Logger LOGGER = LogManager.getLogger(DeliveryAddressDAOImpl.class);

    private ConnectionPool pool = ConnectionPool.getInstance();

    private DeliveryAddressDAOImpl() {
    }

    private static class DeliveryAddressDAOImplHolder {
        private static final DeliveryAddressDAOImpl INSTANCE = new DeliveryAddressDAOImpl();
    }

    public static DeliveryAddressDAOImpl getInstance() {
        return DeliveryAddressDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(DeliveryAddress deliveryAddress) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_ADDRESS.getValue())) {

            statement.setString(1, deliveryAddress.getStreet());
            statement.setInt(2, deliveryAddress.getBuildNumber());
            statement.setInt(3, deliveryAddress.getApartmentNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(DeliveryAddress deliveryAddress) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_ADDRESS.getValue())) {

            statement.setInt(1, deliveryAddress.getBuildNumber());
            statement.setInt(2, deliveryAddress.getApartmentNumber());
            statement.setInt(3, deliveryAddress.getDeliveryAddressId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(DeliveryAddress deliveryAddress) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_ADDRESS.getValue())) {

            statement.setInt(1, deliveryAddress.getDeliveryAddressId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public DeliveryAddress readById(int id) throws DAOException {
        DeliveryAddress address = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_ADDRESS_BY_ID.getValue())) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    address = new DeliveryAddress();
                    address.setDeliveryAddressId(resultSet.getInt(DBFields.DB_ADDRESS_ID.getValue()));
                    address.setStreet(resultSet.getString(DBFields.DB_ADDRESS_STREET.getValue()));
                    address.setBuildNumber(resultSet.getInt(DBFields.DB_ADDRESS_BUILD.getValue()));
                    address.setApartmentNumber(resultSet.getInt(DBFields.DB_ADDRESS_APARTMENT.getValue()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return address;
    }

    @Override
    public List<DeliveryAddress> getAll() throws DAOException {
        List<DeliveryAddress> deliveryAddressList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_ADDRESSES.getValue());
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DeliveryAddress deliveryAddress = buildDeliveryAddress(resultSet);
                deliveryAddressList.add(deliveryAddress);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return deliveryAddressList;
    }

    private DeliveryAddress buildDeliveryAddress(ResultSet resultSet) throws SQLException {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setDeliveryAddressId(resultSet.getInt(DBFields.DB_ADDRESS_ID.getValue()));
        deliveryAddress.setStreet(resultSet.getString(DBFields.DB_ADDRESS_STREET.getValue()));
        deliveryAddress.setBuildNumber(resultSet.getInt(DBFields.DB_ADDRESS_BUILD.getValue()));
        deliveryAddress.setApartmentNumber(resultSet.getInt(DBFields.DB_ADDRESS_APARTMENT.getValue()));

        return deliveryAddress;
    }
}
