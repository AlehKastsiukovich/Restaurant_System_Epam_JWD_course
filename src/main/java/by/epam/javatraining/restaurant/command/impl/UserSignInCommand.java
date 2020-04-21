package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserSignInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = null;
        String login = request.getParameter(JSPParameter.LOGIN.getValue());
        String password = request.getParameter(JSPParameter.PASSWORD.getValue());

        UserService service = ServiceFactory.INSTANCE.getUserService();
        try {
            user = service.signIn(login, password);
            System.out.println(user);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        HttpSession session = request.getSession();

        if (user != null) {
            session.setAttribute(JSPParameter.USER.getValue(), user);
            session.setAttribute(JSPParameter.ROLE.getValue(), user.getRole().getRoleId());
            page = PageType.START_PAGE.getValue();
        } else {
            page = PageType.SIGN_IN_PAGE.getValue();
        }

        return page;
    }
}
