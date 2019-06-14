<%-- 
    Document   : insert
    Created on : Jun 5, 2019, 1:03:23 PM
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
        <h1>Insert</h1>
        <form action="MainController" method="POST">
            ID: <input type="text" name="txtID" value="" /><br/>
            Class <input type="text" name="txtClass" value="" /><br/>
            FName <input type="text" name="txtFirstname" value="" /><br/>
            MName <input type="text" name="txtMiddlename" value="" /><br/>
            LName <input type="text" name="txtLastname" value="" /><br/>
            Address <input type="text" name="txtAddress" value="" /><br/>
            Status <input type="text" name="txtStatus" value="" /><br/>                       
            Password <input type="password" name="txtPassword" value="" />
            Sex <select name="chkSex">
                <option value="0">Male</option>
                <option value="1">Female</option>
            </select>
            <input type="submit" value="Insert" name="action" />
        </form>
    </body>
</html>
