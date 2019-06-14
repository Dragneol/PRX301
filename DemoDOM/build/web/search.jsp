<%-- 
    Document   : welcome
    Created on : Jun 3, 2019, 1:56:15 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.name}!</h1>
        <form action="MainController" method="POST">
            Address: <input type="text" name="txtSearch" value="" />
            <input type="submit" value="Search" name="action" />
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Full Name</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.lastname} ${dto.middlename} ${dto.firstname}</td>
                        <td>${dto.address}</td>
                        <td>${dto.status}</td>
                        <td>
                            <c:url value="MainController" var="Delete">
                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                <c:param name="id" value="${dto.id}"/>
                                <c:param name="action" value="Delete"/>
                            </c:url>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
