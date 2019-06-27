<%-- 
    Document   : ingredient
    Created on : Jun 23, 2019, 9:59:24 AM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EzCo</title>
    </head>
    <body>
        <c:set var="list" value="${requestScope.LIST_INGREDIENT}"/>
        <c:if var="check" test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Link</th>
                        <th>Image</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.id}</td>
                            <td>${dto.name}</td>
                            <td>${dto.link}</td>
                            <td>${dto.price}</td>
                            <td>
                                <img src="${dto.image}" width="100" height="100"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${not check}">
            <h3>No records</h3>
        </c:if>
    </body>
</html>
