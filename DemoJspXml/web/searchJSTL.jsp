<%-- 
    Document   : searchJSTL
    Created on : Jun 21, 2019, 2:33:33 AM
    Author     : dragn
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search wtih jstl xml!</h1>
        <form action="SearchJSTLController" method="POST">
            Fullname <input type="text" name="txtSearch" value="" /> <br/>
            <input type="submit" value="Search" name="action" />
        </form>
        <c:set var="doc" value="${requestScope.DOC}"/>
        <%--<x:parse var="doc" doc="${requestScope.INFO}"/>--%>
        <c:if test="${not empty doc}">
            <c:set var="roleFilter" value="admin"/>
            <x:set var="listUser" select="$doc//account[role=$roleFilter]"/>
            <x:if select="$listUser">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Full name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="user" select="$listUser" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$user/username"/></td>
                                <td><x:out select="$user/fullname"/></td>
                                <td><x:out select="$user/role"/></td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
            </x:if>
        </c:if>
    </body>
</html>
