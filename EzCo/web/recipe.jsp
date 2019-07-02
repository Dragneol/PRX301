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
        <form action="MainController" method="POST">
            Find <input type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <input type="submit" value="ViewRecipes" name="action" />
        </form>
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
                            <td>
                                <c:url value="MainController" var="detail">
                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                    <c:param name="id" value="${dto.id}"/>
                                    <c:param name="action" value="RecipeDetail"/>
                                </c:url>
                                <a href="${detail}">${dto.title}</a>
                            </td>
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
        <c:if test="${not empty param.txtSearch && not check}">
            <h3>No records</h3>
        </c:if>
    </body>
</html>
