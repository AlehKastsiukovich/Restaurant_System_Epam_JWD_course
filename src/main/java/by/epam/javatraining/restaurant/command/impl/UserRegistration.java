package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.entity.User;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistration implements Command {
    private static final Logger LOGGER = Logger.getLogger(UserRegistration.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = null;

        return null;
    }
}
