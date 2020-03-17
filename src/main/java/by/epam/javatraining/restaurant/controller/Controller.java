package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.connectionpool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance().initializeConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println(request.getParameter("login"));
        writer.println(request.getParameter("password"));
        writer.println(request.getRequestURI());
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {

    }
}
