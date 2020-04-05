package by.epam.javatraining.restaurant.command.impl;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.PositionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RedirectToStartPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RedirectToStartPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PositionService service = ServiceFactory.INSTANCE.getPositionService();
        List<Position> positionList = null;

        try {
            positionList = service.getAllPositions();
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        request.setAttribute("positionList", positionList);

        return PageType.START_PAGE.getValue();
    }
}
