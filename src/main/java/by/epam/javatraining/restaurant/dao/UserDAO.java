package by.epam.javatraining.restaurant.dao;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.DAOException;
import java.util.List;

/**
 * Interface describes the opportunity that data source provide to store and
 * restore {@link User} entity
 * @see by.epam.javatraining.restaurant.dao.ModelDAO
 * @see by.epam.javatraining.restaurant.entity.User
 *
 * @author Aleh Kastsiukovich
 *
 */
public interface UserDAO extends ModelDAO<User> {

    /**
     *
     * Add {@link User} to data source
     *
     * @param user {@link User} that must be added to data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void create(User user) throws DAOException;

    /**
     *
     * Update {@link User} from data source
     *
     * @param user {@link User} that must be updated into data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void update(User user) throws DAOException;

    /**
     * Delete {@link User} from data source
     * @param user {@link User} that must be deleted from data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void delete(User user) throws DAOException;

    /**
     *
     * Get all {@link User}'s from data source
     *
     * @return all {@link User}'s from data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    List<User> getAll() throws DAOException;

    /**
     *
     * @param id {@link User} userId
     * @return {@link User} from data source with parameter id
     * @throws DAOException if a data source access error occurs
     */
    User readById(int id) throws DAOException;

    /**
     *
     * @param login parameter for searching {@link User}'s
     * @return {@link User} from datasource with parameter equals <tt>login<tt>
     * @throws DAOException if a data source access error occurs
     */
    User readByLogin(String login) throws DAOException;

    /**
     *
     * @param email parameter for searching {@link User}'s
     * @return {@link User} from datasource with parameter equals <tt>email<tt>
     * @throws DAOException if a data source access error occurs
     */
    User readByEmail(String email) throws DAOException;

    /**
     *
     * @param userId parameter for deleting {@link User}'s from data source
     * @throws DAOException if a data source access error occurs
     */
    void deleteUserById(int userId) throws DAOException;
}
