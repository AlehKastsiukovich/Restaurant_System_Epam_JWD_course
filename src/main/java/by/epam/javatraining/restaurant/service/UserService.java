package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;

public interface UserService {

    void signIn(String login, String password) throws ServiceException;

    void signOut(User user);

    void registration(User user);
}
