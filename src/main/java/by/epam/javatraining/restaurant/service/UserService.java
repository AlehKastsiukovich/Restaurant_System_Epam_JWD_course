package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.User;

public interface UserService {

    void signIn(String login, String password);

    void signOut(User user);

    void registration(User user);
}
