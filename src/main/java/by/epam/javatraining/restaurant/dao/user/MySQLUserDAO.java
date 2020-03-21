package by.epam.javatraining.restaurant.dao.user;

import by.epam.javatraining.restaurant.builder.UserBuilder;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.dao.DBFields;
import by.epam.javatraining.restaurant.dao.query.DBQuery;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO {

    @Override
    public User read(String login) throws DAOException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.READ_USER_BY_LOGIN_QUERY.getValue())) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = buildUser(resultSet);
            }

        } catch (SQLException e) {
            //LOGGER.error(e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public void create(User user) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.CREATE_USER.getValue())) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getFirstName());
            statement.setString(7, user.getLastName());
            statement.setInt(8, user.getRole().getRoleId());
            statement.executeUpdate();

        } catch (SQLException e) {
            //LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.UPDATE_USER.getValue())) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setInt(4, user.getUserId());
            statement.setString(5, user.getLogin());
            statement.executeUpdate();

        } catch (SQLException e) {
            //LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.DELETE_USER.getValue())) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            //LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> userList = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.GET_ALL_USERS.getValue())) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = buildUser(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            //LOGGER.error(e);
            throw new DAOException(e);
        }

        return userList;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new UserBuilder()
                .buildLogin(resultSet.getString(DBFields.DB_USER_LOGIN.getValue()))
                .buildPassword(resultSet.getString(DBFields.DB_USER_PASSWORD.getValue()))
                .buildEmail(resultSet.getString(DBFields.DB_USER_EMAIL.getValue()))
                .buildPhoneNumber(resultSet.getString(DBFields.DB_USER_PHONE_NUMBER.getValue()))
                .buildFirstName(resultSet.getString(DBFields.DB_USER_FIRST_NAME.getValue()))
                .buildLastName(DBFields.DB_USER_LAST_NAME.getValue())
                .build();

        return user;
    }
}
