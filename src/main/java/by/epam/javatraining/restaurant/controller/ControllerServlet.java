package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.pool.ConnectionPool;
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

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Command command = new UserRegistration();
//        String com = request.getParameter("command");
//        String page = command.execute(request, response);
//        request.getRequestDispatcher(page).forward(request, response);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/start_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }
}
