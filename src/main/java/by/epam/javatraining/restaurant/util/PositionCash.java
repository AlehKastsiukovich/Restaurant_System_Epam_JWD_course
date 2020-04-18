package by.epam.javatraining.restaurant.util;

import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.PositionInitializeException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.PositionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.List;

public class PositionCash {
    private static final Logger LOGGER = LogManager.getLogger(PositionCash.class);

    private List<Position> positionList = new LinkedList<>();

    private PositionCash() {
    }

    private static class PositionCashHolder {
        private static final PositionCash INSTANCE = new PositionCash();
    }

    public static PositionCash getInstance() {
        return PositionCashHolder.INSTANCE;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positions) {
        positionList = positions;
    }

    public Position getPositionById(int id) {
        Position position = null;

        for (Position item: positionList) {
            if (item.getPositionId() == id) {
                position = item;
            }
        }

        return position;
    }

    public void initPositions() throws PositionInitializeException {
        PositionService service = ServiceFactory.INSTANCE.getPositionService();
        try {
            positionList = service.getAllPositions();
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new PositionInitializeException(e);
        }
    }
}
