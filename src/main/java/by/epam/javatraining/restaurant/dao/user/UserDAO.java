package by.epam.javatraining.restaurant.dao.user;

import by.epam.javatraining.restaurant.dao.ModelDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;

public interface UserDAO extends ModelDAO<User> {

    User readByLogin(String login) throws DAOException;

    User readByEmail(String email) throws DAOException;
}
