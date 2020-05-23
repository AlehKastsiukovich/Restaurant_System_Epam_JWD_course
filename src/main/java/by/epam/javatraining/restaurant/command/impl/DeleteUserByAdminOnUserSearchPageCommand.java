package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserByAdminOnUserSearchPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserByAdminOnUserSearchPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        UserService service = ServiceFactory.INSTANCE.getUserService();

        try {
            service.deleteUserById(userId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return PageType.ADMIN_USER_SEARCH.getValue();
    }
}
