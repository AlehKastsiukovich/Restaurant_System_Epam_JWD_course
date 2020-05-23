package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

public interface UserDAO extends ModelDAO<User> {

    @Override
    void create(User user) throws DAOException;

    @Override
    void update(User user) throws DAOException;

    @Override
    void delete(User user) throws DAOException;

    @Override
    List<User> getAll() throws DAOException;

    User readById(int id) throws DAOException;

    User readByLogin(String login) throws DAOException;

    User readByEmail(String email) throws DAOException;

    void deleteUserById(int userId) throws DAOException;
}
