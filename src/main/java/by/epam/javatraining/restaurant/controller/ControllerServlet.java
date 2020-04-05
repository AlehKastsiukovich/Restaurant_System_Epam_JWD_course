package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.PageType;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.exception.ServiceException;
import by.epam.javatraining.restaurant.factory.CommandFactory;
import by.epam.javatraining.restaurant.factory.ServiceFactory;
import by.epam.javatraining.restaurant.pool.ConnectionPool;
import by.epam.javatraining.restaurant.service.PositionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance().initializeConnectionPool();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.warn("get******************");
        PositionService service = ServiceFactory.INSTANCE.getPositionService();


        request.setAttribute("test", "cool");
        request.getRequestDispatcher(PageType.START_PAGE.getValue()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOGGER.warn("post*****************");
        Command command = CommandFactory.getInstance().spotCommand(req);
        String page = command.execute(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closeAllPoolConnections();
    }
}
