package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Order;
import com.mysql.cj.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddPositionToCartCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = new Order();

        HttpSession session = request.getSession();

        System.out.println("parameter:" + request.getParameter("id"));

        return PageType.START_PAGE.getValue();
    }
}
