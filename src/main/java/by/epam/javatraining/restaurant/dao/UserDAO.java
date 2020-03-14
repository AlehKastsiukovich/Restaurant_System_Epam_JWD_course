package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;
import by.epam.javatraining.restaurant.dao.query.DBQuery;
import by.epam.javatraining.restaurant.entity.Role;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements ModelDAO<User, String> {
    private final static Logger LOGGER = Logger.getLogger(UserDAO.class);
    private final static  int FIRST_PREPARED_STATEMENT_PARAMETER = 1;
    private final static  int SECOND_PREPARED_STATEMENT_PARAMETER = 2;
    private final static  int THIRD_PREPARED_STATEMENT_PARAMETER = 3;

    private String query = "insert into user (user_id, login, password, email, "
            + "phone_number, first_name, last_name, role_id) value ((?), (?), (?), (?), (?), (?), (?), (?))";

    @Override
    public void create(User user) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
        Connection connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public User read(String login) throws DAOException {
        User user = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
        Connection connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.READ_USER_BY_LOGIN_QUERY.getValue())) {
            statement.setString(FIRST_PREPARED_STATEMENT_PARAMETER, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = buildUser(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
        Connection connection = pool.getConnection();
        pool.initializeConnectionPool();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.DELETE_USER.getValue())) {
            statement.setInt(FIRST_PREPARED_STATEMENT_PARAMETER, user.getUserId());
            statement.setString(SECOND_PREPARED_STATEMENT_PARAMETER, user.getLogin());
            statement.setString(THIRD_PREPARED_STATEMENT_PARAMETER, user.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> userList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
        Connection connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DBQuery.GET_ALL_USERS.getValue())) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = buildUser(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return userList;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        Role role = new Role();

        user.setUserId(resultSet.getInt(DBFields.DB_USER_ID.getValue()));
        user.setLogin(resultSet.getString(DBFields.DB_USER_LOGIN.getValue()));
        user.setPassword(resultSet.getString(DBFields.DB_USER_PASSWORD.getValue()));
        user.setEmail(resultSet.getString(DBFields.DB_USER_EMAIL.getValue()));
        user.setPhoneNumber(resultSet.getString(DBFields.DB_USER_PHONE_NUMBER.getValue()));
        user.setFirstName(resultSet.getString(DBFields.DB_USER_FIRST_NAME.getValue()));
        user.setLastName(resultSet.getString(DBFields.DB_USER_LAST_NAME.getValue()));
        role.setRoleId(resultSet.getInt(DBFields.DB_USER_ROLE_ID.getValue()));
        user.setRole(role);

        return user;
    }
}
