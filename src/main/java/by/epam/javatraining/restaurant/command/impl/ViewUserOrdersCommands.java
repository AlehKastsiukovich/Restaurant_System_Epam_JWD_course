package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewUserOrdersCommands implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewUserOrdersCommands.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        List<Order> orderList;

        User user = (User) session.getAttribute(JSPParameter.USER.getValue());

        if (user != null) {
            try {
                orderList = service.getAllOrdersByUserId(user.getUserId());
                request.setAttribute(JSPParameter.USER_ORDER_LIST.getValue(), orderList);
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        return PageType.PROFILE_USER_ORDERS.getValue();
    }
}
