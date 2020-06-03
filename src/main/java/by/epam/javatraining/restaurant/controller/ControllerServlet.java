package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.exception.PositionInitializeException;
import by.epam.javatraining.restaurant.factory.CommandFactory;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.util.PositionCash;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller"} )
public class ControllerServlet extends HttpServlet {
    private static Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance().initializeConnectionPool();
        PositionCash cash = PositionCash.getInstance();
        try {
            cash.initPositions();
        } catch (PositionInitializeException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.warn("IN DO GET");
        Command command = CommandFactory.getInstance().spotCommand(request);
        LOGGER.warn(request.getMethod());
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.warn("IN DO POST");
        Command command = CommandFactory.getInstance().spotCommand(request);
        LOGGER.warn(request.getMethod());
        String page = command.execute(request, response);
        response.sendRedirect(request.getContextPath()+page);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.getInstance().spotCommand(request);
        LOGGER.warn(request.getMethod());
        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
