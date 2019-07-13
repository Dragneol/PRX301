<%-- 
    Document   : index
    Created on : Jun 11, 2019, 3:42:02 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<content>
    <div>
        <c:forEach items="${sessionScope.DEFAULT}" var="r">
            <div class="product-item">
                <div class="image-product">
                    <img src="${r.image}" alt="Hình ảnh của ${r.title}">
                    <div class="info">
                        <p>${r.title}</p>
                    </div>
                </div>
                <div class="content-product">
                    <div class="content">
                        <h3>Khẩu phần: ${r.ration} người </h3>
                        <p>${r.description}</p>
                    </div>
                    <div>
                        <c:url value="MainController" var="detail">
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
<script src="js/slide.js"></script>
<jsp:include page="footer.jsp"/>
