<%-- 
    Document   : search
    Created on : Jun 6, 2019, 2:32:51 PM
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
        <h1>Hello ${sessionScope.USER}!</h1>
        <form action="MainController" method="POST">
            Address: <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="action" />
        </form>
    </body>
    <br/>
    <c:set var="searchValue" value="${param.txtSearch}"/>
    <c:if test="${not empty searchValue}">
        <c:set var="result" value="${requestScope.RESULT}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Full Name</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.lastname} ${dto.firstname}</td>
                            <td>${dto.sex}</td>
                            <td>${dto.address}</td>
                            <td>
                                ${dto.status}</td>
                            <td>
                                <c:url value="MainController" var="delLink">
                                    <c:param name="action" value="Delete"/>
                                    <c:param name="id" value="${dto.id}"/>
                                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                                </c:url>
                                <a href="${delLink}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h6>No record found</h6>
        </c:if>
    </c:if>
</html>
