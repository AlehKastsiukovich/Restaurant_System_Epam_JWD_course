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
    Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance().initializeConnectionPool();
        try {
            PositionCash.getInstance().initPositions();
        } catch (PositionInitializeException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.warn("get---------------------");
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.warn("post---------------------");
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.warn("handle---------------------");
        Command command = CommandFactory.getInstance().spotCommand(request);
        System.out.println(command);
        String page = command.execute(request, response);
        request.getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
