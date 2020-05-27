package by.java.epam.javatraining.service.impl;

import by.epam.javatraining.restaurant.entity.User;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.service.UserService;
import org.junit.Test;

public class UserServiceImplTest {
    private UserService service = ServiceFactory.INSTANCE.getUserService();

    @Test(expected = ServiceException.class)
    public void testRegisterUserWithNullUserShouldThrowServiceException() throws ServiceException {
        User user = null;
        service.registerUser(user);
    }

    @Test(expected = ServiceException.class)
    public void testRegisterUserWithNotValidParametersShouldThrowServiceException() throws ServiceException {
        User user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375292999615");
        user.setEmail("notvalid@gmail");
        service.registerUser(user);
    }
}
