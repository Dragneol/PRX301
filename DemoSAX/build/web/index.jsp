<%-- 
    Document   : index
    Created on : Jun 10, 2019, 1:03:47 PM
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
        <h1>DEMO SAX</h1>
        <form action="LoginServlet" method="POST">
            Username :<input type="text" name="txtUsername" value="" /><br/>
            Password :<input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action" />
        </form>
        <h1>DEMO StAX</h1>
        <form action="LoginStAXServlet" method="POST">
            Username :<input type="text" name="txtUsername" value="" /><br/>
            Password :<input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action" />
        </form>
    </body>
</html>
