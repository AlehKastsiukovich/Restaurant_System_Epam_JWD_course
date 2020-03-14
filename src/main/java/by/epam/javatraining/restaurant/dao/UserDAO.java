package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;
import by.epam.javatraining.restaurant.entity.Role;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements ModelDAO<User, String> {
    private final static Logger LOGGER = Logger.getLogger(UserDAO.class);

    private static final String readByLoginQuery = "select * from user inner join user_role r"
            + " on user.role_id = r.id"
            + " where user.login = ";

    @Override
    public void create(User user) {

    }

    @Override
    public User read(String login) throws DAOException {
        User user = new User();

        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
        Connection connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(readByLoginQuery + "\'" + login + "\'")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();
                user.setUserId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                role.setRoleId(resultSet.getInt("role_id"));
                user.setRole(role);
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
    public void delete(User user) {

    }
}
