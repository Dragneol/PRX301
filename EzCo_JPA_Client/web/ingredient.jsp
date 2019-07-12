<%-- 
    Document   : ingredient
    Created on : Jun 23, 2019, 9:59:24 AM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<script type="text/javascript">
    var ingredients = [];
    var page = 0;
    let PAGE_SIZE = 6;
    <c:forEach items="${requestScope.LIST_INGREDIENT}" var="dto">
    ingredients.push({
        id: `${dto.id}`,
        name: `${dto.name}`,
        price: ${dto.price},
        link: `${dto.link}`,
        image: `${dto.image}`,
        description: `${dto.description}`
    });
    </c:forEach>
</script>
<content>
    <h1>Tìm kiếm nguyên liệu dựa trên từ khóa</h1>
    <div class="search-center">
        <form action="MainController" method="POST">
            <input class="txtSearch" type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <input class="button" type="submit" value="Lookup" name="action" />
        </form>
    </div>
    <c:if test="${not empty requestScope.LIST_INGREDIENT}" var="check">
        <div id="list-ingredients"></div>
        <div class="load-button" id="load-button-ingredients" >Load more</div>
    </c:if>
    <c:if test="${not check}">
        <h3>Không tìm thấy nguyên liệu, vui lòng thử với từ khóa khác</h3>
    </c:if>
</content>
<script type="text/javascript">
    const loadButtonIng = document.getElementById("load-button-ingredients");

    function loadMoreIngredients() {
        var showedIngredients = ingredients.slice(page * PAGE_SIZE, (page + 1) * PAGE_SIZE).map(item =>
                `<div class="product-item">
        <div class="image-product">
            <img src="\${item.image}" alt="Hình ảnh của \${item.name}" >
            <div class="info">
                <p>\${item.name}</p>
            </div>
        </div>
        <div class="content-product">
            <div class="content">
               <h3>Giá: \${item.price} đồng</h3>
                <p>\${item.description}</p>
            </div>
            <div>
                <a class="button" href="\${item.link}">Tới chỗ mua</a>
            </div>
        </div>
    </div>`);
        document.getElementById("list-ingredients").innerHTML += showedIngredients.join('');
        page++;
        if (page * PAGE_SIZE > ingredients.length) {
            loadButtonIng.outerHTML = "";
        }
    }
    loadMoreIngredients();
    loadButtonIng.addEventListener('click', loadMoreIngredients());
</script>
<jsp:include page="footer.jsp"/>