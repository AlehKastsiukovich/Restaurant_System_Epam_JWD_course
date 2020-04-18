package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.util.PositionCash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddPositionToCartCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Position> positionList = (List<Position>) session.getAttribute(JSPParameter.POSITIONS.getValue());
        if (positionList == null) {
            positionList = new ArrayList<>();
        }

        int positionId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        PositionCash cash = PositionCash.getInstance();
        Position position = cash.getPositionById(positionId);
        positionList.add(position);
        session.setAttribute(JSPParameter.POSITIONS.getValue(), positionList);

        return PageType.START_PAGE.getValue();
    }
}
