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
import java.util.List;

public class DeleteUserByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        UserService service = ServiceFactory.INSTANCE.getUserService();
        List<User> userList = null;

        try {
            service.deleteUserById(userId);
            userList = service.getAllUsers();
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.ALL_USERS.getValue(), userList);

        return PageType.ADMIN_ALL_USERS.getValue();
    }
}
