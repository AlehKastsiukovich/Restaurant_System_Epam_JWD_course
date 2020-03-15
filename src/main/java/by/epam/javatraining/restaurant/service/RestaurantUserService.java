package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.dao.UserDAO;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.factory.MySQLDAOFactory;

public class RestaurantUserService implements UserService {
    private UserDAO dao = MySQLDAOFactory.INSTANCE.getUserDao();

    @Override
    public void signIn(String login, String password) {



    }

    @Override
    public void signOut(User user) {

    }

    @Override
    public void registration(User user) {

    }
}
