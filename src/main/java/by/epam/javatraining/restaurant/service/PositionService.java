package by.epam.javatraining.restaurant.service;

import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;

import java.util.List;

public interface PositionService {

    void createPosition(Position position) throws ServiceException;

    void deletePosition(Position position) throws ServiceException;

    Position getPositionById(int id) throws ServiceException;

    List<Position> getAllPositions() throws ServiceException;

    void updatePrice(Position position) throws ServiceException;
}
