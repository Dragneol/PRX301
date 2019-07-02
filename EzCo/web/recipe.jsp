<%-- 
    Document   : recipe
    Created on : Jun 27, 2019, 3:10:39 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<content>
    <div>
        <c:forEach items="${requestScope.LIST_RECIPE}" var="r">
            <div class="product-item">
                <div class="image-product">
                    <img src="${r.image}" alt="Hình ảnh của ${r.title}">
                    <div class="info">
                        <p>${r.title}</p>
                    </div>
                </div>
                <div class="content-product">
                    <div class="content">
                        <h3>Tổng thời gian nấu: <c:out value="${r.preparetime} + ${r.cookingtime}"/> phút </h3>
                        <p>${r.description}</p>
                    </div>
                    <div>
                        <c:url value="MainController" var="detail">
                            <c:param name="txtSearch" value="${param.txtSearch}"/>
                            <c:param name="id" value="${r.id}"/>
                        </c:url>
                        <form action="${detail}" method="POST">
                            <input class="button" type="submit" value="RecipeDetail" name="action" />
                        </form>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>
</content>
<jsp:include page="footer.jsp"/>