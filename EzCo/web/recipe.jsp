<%-- 
    Document   : recipe
    Created on : Jun 27, 2019, 3:10:39 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<script type="text/javascript">
    var recipes = [];
    var page = 0;
    var PAGE_SIZE = 6;
    <c:forEach items="${requestScope.LIST_RECIPE}" var="r">
    recipes.push({
        id: ${r.id},
        image: `${r.image}`,
        title: `${r.title}`,
        description: `${r.description}`,
        time: ${r.preparetime + r.cookingtime},
    });
    </c:forEach>
</script>
<content>
    <div id="list-recipe"></div>
    <div id="load-button">Load more</div>
</content>
<script type="text/javascript">
    const loadButton = document.getElementById("load-button");

    function loadMoreRecipes() {
        const showedElements = recipes.slice(page * PAGE_SIZE, (page + 1) * PAGE_SIZE).map(item =>
                `<div class="product-item">
                <div class="image-product">
                    <img src="\${item.image}" alt="Hình ảnh của \${item.title}">
                    <div class="info">
                        <p>\${item.title}</p>
                    </div>
                </div>
                <div class="content-product">
                    <div class="content">
                        <h3>Tổng thời gian nấu: \${item.time} phút </h3>
                        <p>\${item.description}</p>
                    </div>
                    <div>
                        <form action="MainController?id=\${item.id}" method="POST">
                            <input class="button" type="submit" value="RecipeDetail" name="action" />
                        </form>
                    </div>
                </div>

            </div>
        `);
        document.getElementById("list-recipe").innerHTML += showedElements.join('');
        page++;
        if (page * PAGE_SIZE > recipes.length) {
//            loadButton.style.display = "none";
            loadButton.outerHTML = "";
        }
    }
    loadMoreRecipes();
    loadButton.addEventListener('click', loadMoreRecipes);
</script>
<jsp:include page="footer.jsp"/>