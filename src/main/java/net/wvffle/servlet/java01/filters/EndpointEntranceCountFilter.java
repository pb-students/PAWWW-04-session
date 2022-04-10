package net.wvffle.servlet.java01.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@WebFilter(filterName = "Endpoint Entrance Counter", urlPatterns = "/*")
public class EndpointEntranceCountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            return;
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = new URL(request.getRequestURL().toString()).getPath();

        HttpSession session = request.getSession();

        Map<String, Integer> endpointEntranceCounter = (Map<String, Integer>) session.getAttribute("endpointEntranceCounter");
        int count = endpointEntranceCounter.getOrDefault(path, 0);
        endpointEntranceCounter.put(path, count + 1);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
