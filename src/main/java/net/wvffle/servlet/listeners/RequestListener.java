package net.wvffle.servlet.listeners;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            boolean loggedIn = (boolean) httpRequest.getSession().getAttribute("logged-in");
            boolean rememberMe = (boolean) httpRequest.getSession().getAttribute("remember-me");

            if (loggedIn && !rememberMe) {
                httpRequest.getSession().setAttribute("logged-in", false);
            }
        }
    }
}
