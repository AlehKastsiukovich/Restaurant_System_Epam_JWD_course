package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.dao.user.UserDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.MySQLDAOFactory;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserDAO dao = MySQLDAOFactory.INSTANCE.getUserDAO();

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user = null;

        try {
            user = dao.read(login);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        if (!(login.equals(user.getLogin()) && password.equals(user.getPassword()))) {
            LOGGER.warn("Wrong password or this user does not exist!");
            throw new ServiceException();
        }

        return user;
    }

    @Override
    public void signOut(User user) {

    }

    @Override
    public void registration(User user) throws ServiceException {
        try {
            dao.create(user);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
