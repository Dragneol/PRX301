<%-- 
    Document   : index
    Created on : Jun 5, 2019, 10:19:50 AM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>DOM Demo By DuongPTH!</h1>
        <form action="MainController" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
