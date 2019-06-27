<%-- 
    Document   : recipe
    Created on : Jun 27, 2019, 3:10:39 PM
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
        <c:set var="list" value="${requestScope.LIST_RECIPE}"/>
        <c:if var="check" test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Ration</th>
                        <th>Prepare Time</th>
                        <th>Cooking Time</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.id}</td>
                            <td><a href="${dto.link}">${dto.title}</a></td>
                            <td>${dto.description}</td>
                            <td>
                                <img src="${dto.image}" width="100" height="100"/>
                            </td>
                            <td>${dto.ration}</td>
                            <td>${dto.preparetime}</td>
                            <td>${dto.cookingtime}</td>
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
