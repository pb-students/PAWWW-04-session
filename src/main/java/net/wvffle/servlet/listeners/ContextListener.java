package net.wvffle.servlet.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("admins", new HashMap<String, String>() {{
            put("wvffle", "123");
        }});

        sce.getServletContext().setAttribute("tokens", new HashSet<UUID>());
    }
}
