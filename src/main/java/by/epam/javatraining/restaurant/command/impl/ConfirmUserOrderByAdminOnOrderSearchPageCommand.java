package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.validator.EnterParameterValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmUserOrderByAdminOnOrderSearchPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmUserOrderByAdminOnOrderSearchPageCommand.class);
    private static final int CONFIRMED_ORDER_STATUS_ID = 2;
    private static final String WRONG_NUMBER_MESSAGE = "Not a number";
    private static final String EMPTY_ORDER_MESSAGE = "Order with this id does not exist";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId;
        Order order = null;
        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        if (EnterParameterValidator.INSTANCE.isNumeric(request.getParameter(JSPParameter.ID.getValue()))) {
            orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
            try {
                order = service.getOrderById(orderId);
                LOGGER.warn(order);
                order.setOrderStatusId(CONFIRMED_ORDER_STATUS_ID);
                service.updateOrder(order);
            } catch (ServiceException e) {
                LOGGER.error(e);
            }

        } else {
            request.setAttribute(JSPParameter.ORDER_SEARCH_MESSAGE.getValue(), WRONG_NUMBER_MESSAGE);
        }

        if (order == null) {
            request.setAttribute(JSPParameter.ORDER_SEARCH_MESSAGE.getValue(), EMPTY_ORDER_MESSAGE);
        }

        request.setAttribute("orderRequest", order);

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
