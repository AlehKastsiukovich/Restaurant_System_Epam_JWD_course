package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;

public interface UserDAO extends ModelDAO<User> {

    User readByLogin(String login) throws DAOException;

    User readByEmail(String email) throws DAOException;
}
