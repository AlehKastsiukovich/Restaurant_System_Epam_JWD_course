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

public class FindUserByIdCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindUserByIdCommand.class);
    private static final String EMPTY_USER_MESSAGE = "User with this id does not exist";
    private static final String WRONG_NUMBER_MESSAGE = "Not a number";
    private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId;
        User user = null;
        UserService service = ServiceFactory.INSTANCE.getUserService();

        if (isNumeric(request.getParameter(JSPParameter.USER_SEARCH_PARAMETER.getValue()))) {
            userId = Integer.parseInt(request.getParameter(JSPParameter.USER_SEARCH_PARAMETER.getValue()));

            try {
                user = service.getUserById(userId);
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        } else {
            request.setAttribute(JSPParameter.USER_SEARCH_MESSAGE.getValue(), WRONG_NUMBER_MESSAGE);
        }

        if (user == null) {
            request.setAttribute(JSPParameter.USER_SEARCH_MESSAGE.getValue(), EMPTY_USER_MESSAGE);
        }

        request.setAttribute(JSPParameter.USER_ID_REQUEST.getValue(), user);

        return PageType.ADMIN_USER_SEARCH.getValue();
    }

    private boolean isNumeric(String requestParameter) {
        return requestParameter.matches(NUMBER_REGEX);
    }
}
