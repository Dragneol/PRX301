<%-- 
    Document   : recipe_detail
    Created on : Jun 28, 2019, 12:17:24 AM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<content>
    <c:set var="recipe" value="${requestScope.RECIPE}"/>
    <c:if var="check" test="${not empty recipe}">
        <div class="food-detail">
            <div class="print-cooking">
                <img class="icon" src="img/png/001-fax.png" alt=""> In công thức
            </div>
            <div class="cooking">
                <h2 class="title">
                    ${recipe.title}
                </h2>
                <p class="description">
                    ${recipe.description}
                </p>
                <div class="detail-cooking">
                    <div class="quick-view">
                        <ul>
                            <li><span> <img class="icon" src="img/png/004-man-user.png" alt=""> Khẩu phần: ${recipe.ration} người</span></li>
                            <li><span> <img class="icon" src="img/png/003-clock.png" alt=""> Chuẩn bị: ${recipe.preparetime} phút</span></li>
                            <li><span> <img class="icon" src="img/png/002-cooking-on-fire.png" alt=""> Thực hiện: ${recipe.cookingtime} phút</span></li>
                        </ul>
                    </div>
                    <div class="image-food">
                        <img src="${recipe.image}" alt="Hình ảnh của ${recipe.title}" width="100%">
                    </div>
                    <div class="content-cooking">
                        <div class="element">
                            <h3 class="title">Thành phần</h3>
                            <div class="content">
                                <c:forEach items="${recipe.ingredientmenu.ingredientdetail}" var="ing">
                                    <a href="#">${ing.quantitive} ${ing.unit} ${ing.name}</a>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="instruction">
                            <h3 class="title">Hướng dẫn</h3>
                            <div class="content">
                                <c:forEach items="${recipe.instructionmenu.instructiondetail}" var="step">
                                    <p>${step.detail}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not check}">
        <h1>Something wrong</h1>
    </c:if>
</content>
<jsp:include page="footer.jsp"/>
