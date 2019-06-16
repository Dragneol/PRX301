<%-- 
    Document   : admin
    Created on : Jun 15, 2019, 11:29:03 PM
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
        <form action="MainController" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Recipe : </td>
                        <td><input type="text" name="homePage" value="http://www.amthuc365.vn/" readonly="readonly" /></td>
                        <td><input type="text" name="subDomain" value="104-cach-che-bien" /></td>
                        <td><input type="submit" value="CrawlRecipe" name="action" /></td>
                    </tr>
                    <tr>
                        <td>Ingredient : </td>
                        <td><input type="text" name="homePage" value="http://nkfood.vn/cua-hang/" readonly="readonly" /></td>
                        <td><input type="text" name="subDomain" value="" /></td>
                        <td><input type="submit" value="CrawlFood" name="action" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
