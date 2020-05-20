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

public class ProcessUserOrderByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ProcessUserOrderByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        LOGGER.warn(orderId);

        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        try {
            Order order = service.getOrderById(orderId);
            LOGGER.warn(order);
            order.setOrderStatusId(2);
            service.updateOrder(order);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return new ViewUnconfirmedOrdersCommand().execute(request, response);
    }
}
