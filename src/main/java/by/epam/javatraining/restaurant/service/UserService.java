package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    void signOut(User user);

    void registerUser(User user) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    void deleteUser(User user) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

    User getUserByEmail(String login) throws ServiceException;

    User getUserById(int id) throws ServiceException;
}
