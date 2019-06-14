<%-- 
    Document   : crawl
    Created on : Jun 12, 2019, 1:00:33 PM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crawl Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.NAME}!</h1>
        <form action="InsertSAXServlet" method="POST">
            <input type="submit" value="Crawl" name="action" />
        </form>
    </body>
</html>
