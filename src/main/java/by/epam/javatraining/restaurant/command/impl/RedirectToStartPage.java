package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.util.PositionCash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RedirectToStartPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Position> positionList = PositionCash.getInstance().getPositionList();
        request.setAttribute("positionList", positionList);
        return PageType.START_PAGE.getValue();
    }
}
