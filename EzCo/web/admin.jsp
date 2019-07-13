<%-- 
    Document   : admin
    Created on : Jun 15, 2019, 11:29:03 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<c:set var="recipeHomeUrl" value="${sessionScope.RECIPE_WEBSITE}"/>
<c:set var="ingredienHomeUrl" value="${sessionScope.INGREDIENT_WEBSITE}"/>
<script type="text/javascript">
    function crawlUsingXMLRequest(type, homePage, subDomain) {
        var result = document.getElementById('crawl-result');
        result.innerHTML = 'Dang cao du lieu....';

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.status === 200 && xhr.readyState === 4) {
                var res = xhr.responseText;
                result.innerHTML = 'Da cao xong!';
                result.innerHTML += res;
            }
        };
        var request = 'MainController?';
        if (type === 'recipe') {
            request += 'recipePage=' + homePage + '&recipeSubDomain=' + subDomain + '&action=CrawlRecipe';
        } else {
            request += 'ingredientPage=' + homePage + '&ingredientSubDomain=' + subDomain + '&action=CrawlIngredient';
        }
        xhr.open('GET', request);
        xhr.send(null);
    }
</script>
<content>
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
                    <!--                    <td><button onclick="crawlUsingXMLRequest('recipe', 'http://www.amthuc365.vn', 0)">Crawl with ajax</button></td>
                                        <td><div id="crawl-result"></div></td>-->
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
<script src="js/slide.js"></script>
<jsp:include page="footer.jsp"/>