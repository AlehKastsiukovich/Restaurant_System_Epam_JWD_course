package by.epam.javatraining.restaurant.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = new Locale("en", "US");
        session.setAttribute("language", locale);
        response.setLocale(locale);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
