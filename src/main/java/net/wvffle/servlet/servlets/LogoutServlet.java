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

@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<UUID> tokens = (Set<UUID>) req.getServletContext().getAttribute("tokens");
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("token")) {
                UUID token = UUID.fromString(cookie.getValue());
                tokens.remove(token);
            }
        }

        Cookie tokenCookie = new Cookie("token", "");
        tokenCookie.setMaxAge(0);
        resp.addCookie(tokenCookie);

        resp.sendRedirect("/login");
    }
}