package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.dao.user.UserDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.MySQLDAOFactory;
import by.epam.javatraining.restaurant.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private static final String DATABASE_CONNECTING_ISSUES_MESSAGE = "Issues with connecting to database!";

    private UserDAO dao = MySQLDAOFactory.INSTANCE.getUserDAO();

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user = null;

        try {
            user = dao.read(login);
        } catch (DAOException e) {
            LOGGER.error(DATABASE_CONNECTING_ISSUES_MESSAGE, e);
            throw new ServiceException(e);
        }

        if (!(user != null && login.equals(user.getLogin()) && password.equals(user.getPassword()))) {
            LOGGER.warn("Wrong password or this user does not exist!");
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public void signOut(User user) {

    }

    @Override
    public void registerUser(User user) throws ServiceException {
        if (!UserValidator.INSTANCE.validateUser(user)) {
            LOGGER.warn("User parameters set incorrectly");
            throw new ServiceException();
        }

        try {
            dao.create(user);
        } catch (DAOException e) {
            LOGGER.error(DATABASE_CONNECTING_ISSUES_MESSAGE, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> userList = null;

        try {
            userList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(DATABASE_CONNECTING_ISSUES_MESSAGE, e);
            throw new ServiceException(e);
        }

        return userList;
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        if (!UserValidator.INSTANCE.validateUser(user)) {
            LOGGER.warn("User parameters are incorrect!");
            throw new ServiceException();
        }

        try {
            dao.delete(user);
        } catch (DAOException e) {
            LOGGER.error(DATABASE_CONNECTING_ISSUES_MESSAGE, e);
            throw new ServiceException(e);
        }
    }
}
