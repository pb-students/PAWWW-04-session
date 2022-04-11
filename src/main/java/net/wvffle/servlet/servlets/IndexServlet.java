package net.wvffle.servlet.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@WebServlet(name = "index", value = "")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean loggedIn = false;
        Set<UUID> tokens = (Set<UUID>) req.getServletContext().getAttribute("tokens");
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("token")) {
                loggedIn = tokens.contains(UUID.fromString(cookie.getValue()));
            }
        }
        if (!loggedIn) {
            resp.sendRedirect("/login");
            return;
        }

        req.getRequestDispatcher("protected.jsp").forward(req, resp);
    }
}