package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.builder.UserBuilder;
import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistration implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserRegistration.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = createUserFromRegistrationForm(request);

        UserService service = ServiceFactory.INSTANCE.getUserService();
        try {
            service.registration(user);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return PageType.HOME_PAGE.getValue();
    }

    private User createUserFromRegistrationForm(HttpServletRequest request) {
        User user = new UserBuilder()
                .buildLogin(request.getParameter("login"))
                .buildFirstName(request.getParameter("firstName"))
                .buildLastName(request.getParameter("lastName"))
                .buildEmail(request.getParameter("email"))
                .buildPhoneNumber(request.getParameter("phoneNumber"))
                .buildPassword(request.getParameter("password"))
                .build();

        return user;
    }
}
