<%-- 
    Document   : recipe_detail
    Created on : Jun 28, 2019, 12:17:24 AM
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
        <c:set var="recipe" value="${requestScope.RECIPE}"/>
        <c:if var="check" test="${not empty recipe}">
            <h1>Recipe Detail</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Ration</th>
                        <th>Prepare Time</th>
                        <th>Cooking Time</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${recipe.title}</td>
                        <td>${recipe.description}</td>
                        <td>
                            <img src="${recipe.image}" width="100" height="100"/>
                        </td>
                        <td>${recipe.ration}</td>
                        <td>${recipe.preparetime}</td>
                        <td>${recipe.cookingtime}</td>
                    </tr>
                </tbody>
            </table>
            <br/>
            <h1>Recipe Steps</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>Num Step</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${recipe.instructionmenu.instructiondetail}" var="step">
                        <tr>
                            <td>${step.numstep}</td>
                            <td>${step.detail}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br/>
            <h1>Ingredient Menu</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Quantitive</th>
                        <th>Unit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${recipe.ingredientmenu.ingredientdetail}" var="ing">
                        <tr>
                            <td>${ing.name}</td>
                            <td>${ing.quantitive}</td>
                            <td>${ing.unit}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not check}">
            <h1>Something wrong</h1>
        </c:if>
    </body>
</html>
