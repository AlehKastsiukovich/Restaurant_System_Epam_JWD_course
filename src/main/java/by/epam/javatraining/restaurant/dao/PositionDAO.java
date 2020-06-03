package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

/**
 * Interface describes the opportunity that data source provide to store and
 * restore {@link Position} entity
 * @see by.epam.javatraining.restaurant.dao.ModelDAO
 * @see by.epam.javatraining.restaurant.entity.Position
 *
 * @author Aleh Kastsiukovich
 *
 */
public interface PositionDAO extends ModelDAO<Position> {

    /**
     * Add {@link Position} to data source
     *
     * @param position {@link Position} that must be added
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void create(Position position) throws DAOException;

    /**
     * Update {@link Position} from data source
     *
     * @param position {@link Position} that must be updated
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void update(Position position) throws DAOException;

    /**
     * Delete {@link Position} from data source
     *
     * @param position {@link Position} that must be deleted
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void delete(Position position) throws DAOException;

    /**
     * Get all {@link Position}'s from data source
     *
     * @return List of {@link Position}'s from data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    List<Position> getAll() throws DAOException;

    /**
     * Get {@link Position} from data source with {@link Position} id equal <tt>id<tt>
     *
     * @param id parameter for searching {@link Position}
     * @return {@link Position} from data source
     * @throws DAOException if a data source access error occurs
     */
    Position readById(int id) throws DAOException;
}
