<%-- 
    Document   : index
    Created on : Jun 17, 2019, 12:58:07 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Page!</h1>
        <form action="SearchController" method="POST">
            Search Value :  <input type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <input type="submit" value="Search" name="action" />
        </form>
        <c:if test="${requestScope.INFO != null}">
            <c:if test="${not empty requestScope.INFO}" var="checkInfo">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Password</th>
                            <th>Address</th>
                            <th>Status</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.id}</td>
                                <td>${dto.lastname} ${dto.middlename} ${dto.firstname}</td>
                                <td>${dto.gender}</td>
                                <td>${dto.password}</td>
                                <td>${dto.address}</td>
                                <td>${dto.status}</td>
                                <td>Delete</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${not checkInfo}">
                <p>No record found</p>
            </c:if>
        </c:if>
    </body>
</html>
