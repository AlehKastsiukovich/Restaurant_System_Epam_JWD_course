package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

public interface PositionDAO extends ModelDAO<Position> {

    @Override
    void create(Position position) throws DAOException;

    @Override
    void update(Position position) throws DAOException;

    @Override
    void delete(Position position) throws DAOException;

    @Override
    Position readById(int id) throws DAOException;

    @Override
    List<Position> getAll() throws DAOException;
}
