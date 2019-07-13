<%-- 
    Document   : admin
    Created on : Jun 15, 2019, 11:29:03 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<content>
    <c:set var="recipeHomeUrl" value="${sessionScope.RECIPE_WEBSITE}"/>
    <c:set var="ingredienHomeUrl" value="${sessionScope.INGREDIENT_WEBSITE}"/>
    <form action="MainController" method="POST">
        <table border="0">
            <tbody>
                <tr>
                    <td>Recipe : </td>
                    <td><input type="text" name="recipePage" value="${recipeHomeUrl.homeurl}" readonly="readonly" /></td>
                    <td>
                        <select name='recipeSubDomain'>
                            <c:forEach items="${recipeHomeUrl.subdomains.subdomain}" var="temp">
                                <option value='${temp.id}'>${temp.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" value="CrawlRecipe" name="action" /></td>
                </tr>
                <tr>
                    <td>Ingredient : </td>
                    <td><input type="text" name="ingredientPage" value="${ingredienHomeUrl.homeurl}" readonly="readonly" /></td>
                    <td>
                        <select name='ingredientSubDomain'>
                            <c:forEach items="${ingredienHomeUrl.subdomains.subdomain}" var="temp">
                                <option value='${temp.id}'>${temp.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" value="CrawlIngredient" name="action" /></td>
                </tr>
            </tbody>
        </table>
    </form>
</content>
<jsp:include page="footer.jsp"/>