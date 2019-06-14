<%-- 
    Document   : index
    Created on : Jun 3, 2019, 12:59:51 PM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController">
            <input type="text" name="txtUsername" value="" /><br/>
            <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action"/><br/>
        </form>
        <a href="insert.jsp">Insert</a>
    </body>
</html>
