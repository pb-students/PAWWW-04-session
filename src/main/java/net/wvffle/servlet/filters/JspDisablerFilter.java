package net.wvffle.servlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@WebFilter(filterName = "Disable Jsp", urlPatterns = "/*")
public class JspDisablerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            return;
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = new URL(request.getRequestURL().toString()).getPath();

        if (path.endsWith(".jsp")) {
            ((HttpServletResponse) servletResponse).setStatus(401);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
