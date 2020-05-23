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

public class FindUserByLoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindUserByLoginCommand.class);
    private static final String EMPTY_USER_MESSAGE = "User with this login does not exist";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("userParameter");
        UserService service = ServiceFactory.INSTANCE.getUserService();
        User user = null;

        try {
            user = service.getUserByLogin(login);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        if (user == null) {
            request.setAttribute(JSPParameter.USER_SEARCH_MESSAGE.getValue(), EMPTY_USER_MESSAGE);
        }

        request.setAttribute(JSPParameter.USER_ID_REQUEST.getValue(), user);

        return PageType.ADMIN_USER_SEARCH.getValue();
    }
}
