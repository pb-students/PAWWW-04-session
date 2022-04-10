package net.wvffle.servlet.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "index", value = "")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean loggedIn = (boolean) req.getSession().getAttribute("logged-in");
        if (!loggedIn) {
            resp.sendRedirect("/login");
            return;
        }

        req.getRequestDispatcher("protected.jsp").forward(req, resp);
    }
}