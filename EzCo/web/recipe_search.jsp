<%-- 
    Document   : recipe_search
    Created on : Jul 6, 2019, 11:56:49 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<script type="text/javascript">
    var ingredients = [];
    var suggestions = [];
    var tags = [];
    var suggestionIndex = 0;
    <c:set value="${sessionScope.RECIPE_WEBSITE.subdomains.subdomain}" var="jspIng"/>
    <c:forEach items="${jspIng}" var="temp" varStatus="counter">
        <c:if test="${counter.count != 1}">ingredients.push({id: ${temp.id}, value: `${temp.value}`});</c:if>
    </c:forEach>
            function suggest(event) {
                if (event.keyCode === 13) {
                    const suggestions = document.getElementsByClassName("sug");
                    if (suggestions.length > suggestionIndex) {
                        tags.push({
                            id: suggestions[suggestionIndex].dataset.id,
                            value: suggestions[suggestionIndex].innerHTML
                        });
                    }
                    renderSuggestions();
                    document.getElementById("tags-included").innerHTML = tags.map(item => `<span>\${item.value}</span>`).join('');
                    document.getElementById("tags").value = tags.map(item => item.id);
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
                        return item.value.toLowerCase().includes(text.toLowerCase());
                    });
                    suggestionIndex = 0;
                    renderSuggestions();
                }
            }

            function renderSuggestions() {
                const suggestionComponents = suggestions
                        .filter(item => !(tags.map(i => i.value)).includes(item.value))
                        .map((item, index) =>
                                `<span class="sug \${index === suggestionIndex ? 'choosing' : ''}" data-id="\${item.id}">\${item.value}</span>`
                        );
                const area = document.getElementById("suggestion");
                area.innerHTML = suggestionComponents.join('');
                area.style.visibility = 'visible';
            }

            function loadSuggestions() {
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
<content>
    <h1>Recipes Advance Search</h1>
    <div class="search-center">
        <form id="searchForm" autocomplete="off">
            <input id="input-search" 
                   class="txtSearch" 
                   type="text" 
                   placeholder="i.e: gà" name="txtSearch" 
                   value="${param.txtSearch}" />
        </form>
        <div id="tags-included"></div>
        <div id="suggestion"></div>
        <form action="MainController" method="POST" >
            <input id="tags" type="hidden" name="tags" value="" />
            <input class="button" type="submit" value="AdvanceSearch" name="action" />
        </form>
    </div>
    <script type="text/javascript">loadSuggestions();</script>
</content>
<jsp:include page="footer.jsp"/>