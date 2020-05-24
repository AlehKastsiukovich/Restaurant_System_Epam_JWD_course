package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteOrderByAdminOnOrderSearchPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteOrderByAdminOnOrderSearchPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        int userId = Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue()));
        List<Order> orderList = null;
        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        try {
            service.deleteOrderById(orderId);
            orderList = service.getAllOrdersByUserId(userId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.USER_ORDER_LIST.getValue(), orderList);

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
