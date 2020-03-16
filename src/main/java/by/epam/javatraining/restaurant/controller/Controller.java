package by.epam.javatraining.restaurant.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("kaffka")) {
            request.getRequestDispatcher("/adminpage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
