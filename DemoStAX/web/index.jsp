<%-- 
    Document   : index
    Created on : Jun 14, 2019, 12:16:43 PM
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
        <h1>DEMO StAX</h1>
        <form action="LoginSevlet" method="POST">
            Username :<input type="text" name="txtUsername" value="" /><br/>
            Password :<input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action" />
        </form>
    </body>
</html>
