package net.wvffle.servlet.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "api-login", value = "/login")
public class ApiLoginServlet extends HttpServlet {
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

        Map<String, String> admins = (Map<String, String>) getServletContext().getAttribute("admins");
        if (admins.containsKey(username) && Objects.equals(admins.get(username), password)) {
            req.getSession().setAttribute("logged-in", true);
            resp.sendRedirect("/");
            return;
        }

        resp.setStatus(401);
    }
}