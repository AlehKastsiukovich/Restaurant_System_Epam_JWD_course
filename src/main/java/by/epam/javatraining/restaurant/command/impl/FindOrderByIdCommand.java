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
import java.util.LinkedList;
import java.util.List;

public class FindOrderByIdCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindOrderByIdCommand.class);
    private static final String WRONG_NUMBER_MESSAGE = "Not a number";
    private static final String EMPTY_ORDER_MESSAGE = "Order with this id does not exist";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId;
        Order order = null;
        List<Order> orderList = new LinkedList<>();
        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        if (EnterParameterValidator.INSTANCE.isNumeric(
                request.getParameter(JSPParameter.ORDER_SEARCH_PARAMETER.getValue()))) {
            orderId = Integer.parseInt(request.getParameter(JSPParameter.ORDER_SEARCH_PARAMETER.getValue()));

            try {
                order = service.getOrderById(orderId);

                if (order != null) {
                    orderList.add(order);
                } else {
                    request.setAttribute(JSPParameter.ORDER_SEARCH_MESSAGE.getValue(), EMPTY_ORDER_MESSAGE);
                }

            } catch (ServiceException e) {
                LOGGER.error(e);
            }

        } else {
            request.setAttribute(JSPParameter.ORDER_SEARCH_MESSAGE.getValue(), WRONG_NUMBER_MESSAGE);
        }

        request.setAttribute(JSPParameter.USER_ORDER_LIST.getValue(), orderList);

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
