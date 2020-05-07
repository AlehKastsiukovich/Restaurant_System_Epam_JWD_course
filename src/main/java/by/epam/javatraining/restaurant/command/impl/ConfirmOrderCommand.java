package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.DeliveryAddress;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.DeliveryAddressService;
import by.epam.javatraining.restaurant.service.ItemOrderService;
import by.epam.javatraining.restaurant.service.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ConfirmOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute(JSPParameter.ORDER.getValue());
        Map<Position, Integer> positionIntegerMap = (Map<Position, Integer>) session
                .getAttribute(JSPParameter.SESSION_POSITIONS.getValue());
        DeliveryAddress address = createDeliveryAddress(request);

        if (order != null) {
            order.setDeliveryAddress(address);
            OrderService orderService = ServiceFactory.INSTANCE.getOrderService();
            DeliveryAddressService addressService = ServiceFactory.INSTANCE.getDeliveryAddressService();
            ItemOrderService itemOrderService = ServiceFactory.INSTANCE.getItemOrderService();
            try {
                addressService.createDeliveryAddress(order.getDeliveryAddress());
                orderService.createOrder(order);
                itemOrderService.createItemOrder(positionIntegerMap, order);
                session.removeAttribute(JSPParameter.SESSION_POSITIONS.getValue());
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
