<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <body>
        <ol>
            <%
                Map<String, Integer> endpointEntranceCounter = (Map<String, Integer>) session.getAttribute("endpointEntranceCounter");
                List<Map.Entry<String, Integer>> entries = endpointEntranceCounter.entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getValue))
                        .collect(Collectors.toList());

                Collections.reverse(entries);
            %>

            <% for (Map.Entry<String, Integer> entry : entries) { %>
            <li>
                <a href="<%= entry.getKey() %>"><%= entry.getKey() %></a>
                (<%= entry.getValue() %>)
            </li>
            <% } %>
        </ol>

        <a href="/endpoint/a">Endpoint A</a>
        <a href="/endpoint/b">Endpoint B</a>
        <a href="/endpoint/c">Endpoint C</a>

        <%
            boolean loggedIn = false;
            Set<UUID> tokens = (Set<UUID>) request.getServletContext().getAttribute("tokens");
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("token")) {
                    loggedIn = tokens.contains(UUID.fromString(cookie.getValue()));
                }
            }
        %>
        <% if (loggedIn) { %>
        <form method="post" action="/logout">
            <input type="submit" value="logout" />
        </form>
        <% } %>
    </body>
</html>