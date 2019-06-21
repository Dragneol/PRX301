<%-- 
    Document   : login
    Created on : Jun 19, 2019, 12:43:43 PM
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
        <h1>Welcome e-Hell Bank</h1>
        <form action="processLogin.jsp" method="POST">
            Username: <input type="text" name="txtUsername" value="" /><br/>
            Pin <input type="password" name="txtPin" value="" /><br/>
            <input type="submit" value="Login" name="action" />
        </form>
    </body>
</html>
