<%-- 
    Document   : header
    Created on : Jul 2, 2019, 7:28:10 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>EzCo</title>
        <link rel="stylesheet" href="css/slide.css">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/detail.css">
        <script type="text/javascript">
            var ingredients = [];
            var suggestions = [];
            var tags = [];
            var suggestionIndex = 0;
            <c:set value="${sessionScope.RECIPE_WEBSITE.subdomains.subdomain}" var="jspIng"/>

            <c:forEach items="${jspIng}" var="temp" varStatus="counter">
                <%--<c:if test="${counter.count != 1}">--%>
            ingredients.push(`${temp.value}`);
                <%--</c:if>--%>
            </c:forEach>
            function suggest(event) {
                if (event.keyCode === 13) {
                    const suggestions = document.getElementsByClassName("sug");
                    if (suggestions.length > suggestionIndex) {
                        tags.push(suggestions[suggestionIndex].innerHTML);
                    }
                    renderSuggestions();
                    document.getElementById("tags-included").innerHTML = tags.map(item => `<span>\${item}</span>`).join('');
                    document.getElementById("tags").value = tags;
                } else if (event.keyCode === 38) {
                    // up
                    if (suggestionIndex > 0) {
                        suggestionIndex--;
                        renderSuggestions();
                    }
                } else if (event.keyCode === 40) {
                    //down
                    if (suggestionIndex < suggestions.length - 1) {
                        suggestionIndex++;
                        renderSuggestions();
                    }
                } else if (event.keyCode === 27) {
                    // esc
                    document.getElementById("suggestion").style.visibility = 'hidden';
                } else {
                    const text = event.target.value;
                    suggestions = ingredients.filter(item => {
                        return item.toLowerCase().includes(text.toLowerCase());
                    });
                    suggestionIndex = 0;
                    renderSuggestions();
                }
            }

            function renderSuggestions() {
                const suggestionComponents = suggestions
                        .filter(item => !tags.includes(item))
                        .map((item, index) =>
                                `<span class="sug \${index === suggestionIndex ? 'choosing' : ''}">\${item}</span>`
                        );
                const area = document.getElementById("suggestion");
                area.innerHTML = suggestionComponents.join('');
                area.style.visibility = 'visible';
            }

            function load() {
                document.getElementById("input-search").addEventListener('keyup', suggest);
                document.getElementById("input-search").addEventListener('blur', function () {
                    document.getElementById("suggestion").style.visibility = 'hidden';
                });
                document.getElementById("searchForm").addEventListener('submit', function (e) {
                    e.preventDefault();
                    return false;
                });
            }
        </script>
    </head>

    <body onload="load()">
        <header>
            <div class="logo-left">
                <span>EzCo</span>
            </div>
            <div class="list-tag">
                <ul class="wrap-list">
                    <li class="item-list"><a href="IndexController"><span>Recipe</span></a></li>
                        <c:url value="MainController" var="ingIndex">
                            <c:param name="action" value="Lookup"/>
                        </c:url>
                    <li class="item-list"><a href="${ingIndex}"><span>Ingredient</span></a></li>
                </ul>
            </div>

            <div class="search-right">
                <form id="searchForm" autocomplete="off">
                    <input id="input-search" class="txtSearch" type="text" name="txtSearch" value="${param.txtSearch}" />
                </form>
                <div id="tags-included"></div>
                <div id="suggestion"></div>
                <form action="MainController" method="POST" >
                    <input id="tags" type="hidden" name="tags" value="" />
                    <input class="button" type="submit" value="Search" name="action" />
                </form>
            </div>
        </header>
