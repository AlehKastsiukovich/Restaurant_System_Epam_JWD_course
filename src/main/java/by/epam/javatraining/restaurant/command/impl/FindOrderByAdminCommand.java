package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.PageType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindOrderByAdminCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
