package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute(JSPParameter.ORDER.getValue());
        DeliveryAddress address = createDeliveryAddress(request);

        if (order != null) {
            order.setDeliveryAddress(address);
            OrderService service = OrderServiceImpl.getInstance();
            try {
                service.createOrder(order);
            } catch (ServiceException e) {
                LOGGER.error(e);
            }

            return PageType.START_PAGE.getValue();
        }

        return PageType.ADDRESS_FORM_PAGE.getValue();
    }

    private DeliveryAddress createDeliveryAddress(HttpServletRequest request) {
        DeliveryAddress address = new DeliveryAddress();
        address.setStreet(request.getParameter(JSPParameter.STREET.getValue()));
        address.setBuildNumber(Integer.parseInt(request.getParameter(JSPParameter.BUILD_NUMBER.getValue())));
        address.setApartmentNumber(Integer.parseInt(request.getParameter(JSPParameter.APARTMENTS_NUMBER.getValue())));

        return address;
    }
}
