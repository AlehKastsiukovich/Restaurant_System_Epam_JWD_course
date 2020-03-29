package by.epam.javatraining.restaurant.service.impl;

import by.epam.javatraining.restaurant.dao.UserDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private UserDAO dao = DAOFactoryImpl.INSTANCE.getUserDAO();

    private UserServiceImpl() {
    }

    private static class UserServiceImplHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.INSTANCE;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user = null;

        if (UserValidator.INSTANCE.validateLogin(login) && UserValidator.INSTANCE.validatePassword(password)) {
            user = getUserByLogin(login);

            if (user == null || !password.equals(user.getPassword())) {
                throw new ServiceException("Wrong password or user does not exist!");
            }
        }

        return user;
    }

    @Override
    public void signOut(User user) {
        //soon
    }

    @Override
    public void registerUser(User user) throws ServiceException {
        if (!UserValidator.INSTANCE.validateUser(user)) {
            LOGGER.warn("User parameters set incorrectly");
            throw new ServiceException("Can't register user. User parameters set incorrectly");
        }

        if (isLoginExist(user.getLogin())) {
            LOGGER.warn("Login is Used");
            throw new ServiceException("Can't register user with this login. Login is used!");
        }

        if (isEmailExist(user.getEmail())) {
            LOGGER.warn("Email is used");
            throw new ServiceException("Can't register user with this email. Email is used!");
        }

        try {
            dao.create(user);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> userList = null;

        try {
            userList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return userList;
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        if (!UserValidator.INSTANCE.validateUser(user)) {
            LOGGER.warn("User parameters are incorrect!");
            throw new ServiceException("Can't delete user!");
        }

        try {
            dao.delete(user);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public User getUserByLogin(String login) throws ServiceException {
        User user;

        try {
            user = dao.readByLogin(login);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        User user;

        try {
            user = dao.readByEmail(email);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        User user;

        try {
            user = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return user;
    }

    private boolean isLoginExist(String login) throws ServiceException {
        User user;

        try {
            user = dao.readByLogin(login);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }

        return user != null;
    }

    private boolean isEmailExist(String email) throws ServiceException {
        User user;

        try {
            user = dao.readByEmail(email);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }

        return user != null;
    }
}
