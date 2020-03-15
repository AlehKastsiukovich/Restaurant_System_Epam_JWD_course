package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;

public class UserServiceIml implements UserService {

    @Override
    public void signIn(String login, String password) throws ServiceException {
        if (login == null || password == null) {
            throw new ServiceException();
        }


    }

    @Override
    public void signOut(User user) {

    }

    @Override
    public void registration(User user) {

    }
}
