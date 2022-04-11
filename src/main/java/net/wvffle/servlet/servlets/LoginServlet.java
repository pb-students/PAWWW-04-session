package net.wvffle.servlet.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean loggedIn = (boolean) req.getSession().getAttribute("logged-in");
        if (loggedIn) {
            resp.sendRedirect("/");
            return;
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean rememberMe = Objects.equals(req.getParameter("rememberMe"), "on");

        Set<UUID> tokens = (Set<UUID>) req.getServletContext().getAttribute("tokens");
        Map<String, String> admins = (Map<String, String>) getServletContext().getAttribute("admins");
        if (admins.containsKey(username) && Objects.equals(admins.get(username), password)) {
            UUID token = UUID.randomUUID();
            tokens.add(token);
            Cookie tokenCookie = new Cookie("token", token.toString());
            tokenCookie.setMaxAge(rememberMe ? 3600 * 365 : -1);
            resp.addCookie(tokenCookie);
            resp.sendRedirect("/");
            return;
        }

        resp.setStatus(401);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}