<%-- 
    Document   : index
    Created on : Jun 11, 2019, 3:42:02 PM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EzCo</title>
    </head>
    <body>
        <h1>Hello User of EzCo!</h1>
        <form action="MainController" method="POST">
            <input type="submit" value="ViewIngredients" name="action" />
        </form>
        <form action="MainController" method="POST">
            <input type="submit" value="ViewRecipes" name="action" />
        </form>
    </body>
</html>
