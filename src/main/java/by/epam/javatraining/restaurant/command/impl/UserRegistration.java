package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.builder.UserBuilder;
import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.service.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegistration implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = createUserFromRegistrationForm(request);

        UserService service = new UserServiceImpl();
        try {
            service.registration(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return "/index.jsp";
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
