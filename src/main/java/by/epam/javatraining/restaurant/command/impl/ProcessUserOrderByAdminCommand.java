package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.builder.BillBuilder;
import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.entity.Bill;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.OrderService;
import by.epam.javatraining.restaurant.service.UserService;
import by.epam.javatraining.restaurant.util.EmailSender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class ProcessUserOrderByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ProcessUserOrderByAdminCommand.class);
    private static final int CONFIRMED_ORDER_STATUS_ID = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        HttpSession session = request.getSession();
        User administrator = (User) session.getAttribute(JSPParameter.USER.getValue());

        try {
            Order order = service.getOrderById(orderId);
            order.setOrderStatusId(CONFIRMED_ORDER_STATUS_ID);
            service.updateOrder(order);

            Bill bill = createBill(order, administrator);
            sendBillToEmail(bill, order);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return new ViewUnconfirmedOrdersCommand().execute(request, response);
    }

    private void sendBillToEmail(Bill bill, Order order) throws ServiceException {
        EmailSender sender = EmailSender.getInstance();
        UserService service = ServiceFactory.INSTANCE.getUserService();
        User user = service.getUserById(order.getCustomerId());

        sender.sendEmail(bill.toString(), user.getEmail());
    }

    private Bill createBill(Order order, User user) {
        return new BillBuilder()
                .buildOrder(order)
                .buildUser(user)
                .buildDate(new Date())
                .build();
    }
}
