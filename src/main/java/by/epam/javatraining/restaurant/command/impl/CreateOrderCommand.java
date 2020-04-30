package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.builder.OrderBuilder;
import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Order;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

public class CreateOrderCommand implements Command {
    private static final Integer CURRENT_POSITIONS_SUM = 0;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(JSPParameter.USER.getValue());
        Map<Position, Integer> cartMap = (Map<Position, Integer>) session
                .getAttribute(JSPParameter.SESSION_POSITIONS.getValue());

        BigDecimal totalPrice;
        if (user != null && cartMap != null) {
            totalPrice = calculateTotalPrice(cartMap);

            Order order = new OrderBuilder()
                    .buildTotalPrice(totalPrice)
                    .buildCustomerId(user.getUserId())
                    .build();
            session.setAttribute(JSPParameter.ORDER.getValue(), order);

            return PageType.ADDRESS_FORM_PAGE.getValue();
        }

        return PageType.CART_PAGE.getValue();
    }

    private BigDecimal calculateTotalPrice(Map<Position, Integer> map) {
        BigDecimal totalPrice = new BigDecimal(CURRENT_POSITIONS_SUM);

        for (Map.Entry<Position, Integer> entry : map.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getItemPrice().multiply(new BigDecimal(entry.getValue())));
        }

        return totalPrice;
    }
}
