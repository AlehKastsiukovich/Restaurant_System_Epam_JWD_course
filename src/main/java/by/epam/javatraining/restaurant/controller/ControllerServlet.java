package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.impl.UserRegistration;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
       ConnectionPool.getInstance().initializeConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = new UserRegistration();
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
