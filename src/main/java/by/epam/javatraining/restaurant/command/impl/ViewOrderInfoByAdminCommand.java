package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.ItemOrder;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.ItemOrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewOrderInfoByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewOrderInfoByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<ItemOrder> itemOrderList = null;
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        ItemOrderService service = ServiceFactory.INSTANCE.getItemOrderService();

        try {
            itemOrderList = service.getItemOrdersByOrderId(orderId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.ITEM_ORDER_LIST.getValue(), itemOrderList);

        return PageType.ADMIN_ORDER_INFO.getValue();
    }
}
