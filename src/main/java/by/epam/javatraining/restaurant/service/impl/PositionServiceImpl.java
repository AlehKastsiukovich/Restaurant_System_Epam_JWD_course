package by.epam.javatraining.restaurant.service.impl;

import by.epam.javatraining.restaurant.dao.PositionDAO;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.DAOException;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.DAOFactoryImpl;
import by.epam.javatraining.restaurant.service.PositionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;

public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LogManager.getLogger(PositionServiceImpl.class);

    private PositionDAO dao = DAOFactoryImpl.INSTANCE.getPositionDAO();

    private PositionServiceImpl() {
    }

    private static class PositionServiceImplHolder {
        private static final PositionServiceImpl INSTANCE = new PositionServiceImpl();
    }

    public static PositionServiceImpl getInstance() {
        return PositionServiceImplHolder.INSTANCE;
    }

    @Override
    public void createPosition(Position position) throws ServiceException {
        try {
            dao.create(position);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePosition(Position position) throws ServiceException {
        try {
            dao.delete(position);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Position getPositionById(int id) throws ServiceException {
        Position position;

        try {
            position = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return position;
    }

    @Override
    public List<Position> getAllPositions() throws ServiceException {
        List<Position> positionList;

        try {
            positionList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return positionList;
    }

    @Override
    public void updatePrice(Position position) throws ServiceException {
        try {
            dao.update(position);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
