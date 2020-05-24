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

public class ConfirmUserOrderByAdminOnOrderSearchPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmUserOrderByAdminOnOrderSearchPageCommand.class);
    private static final int CONFIRMED_ORDER_STATUS_ID = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = null;
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));

        try {
            order = service.getOrderById(orderId);
            order.setOrderStatusId(CONFIRMED_ORDER_STATUS_ID);
            service.updateOrder(order);

        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
