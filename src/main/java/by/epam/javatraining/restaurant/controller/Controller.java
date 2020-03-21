package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.pool.ConnectionPool;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.initializeConnectionPool();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String log = request.getParameter("login");
        String pas = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");

        PrintWriter writer = response.getWriter();
        writer.println(log);
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Locale locale = new Locale("en", "US");
//        session.setAttribute("language", locale);
//        response.setLocale(locale);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
}
