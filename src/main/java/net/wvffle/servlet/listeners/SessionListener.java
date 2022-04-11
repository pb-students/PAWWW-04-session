package net.wvffle.servlet.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.HashMap;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("endpointEntranceCounter", new HashMap<String, Integer>());
        se.getSession().setAttribute("logged-in", false);
        se.getSession().setAttribute("remember-me", false);
    }
}
