<%-- 
    Document   : editproduct
    Created on : Nov 2, 2018, 8:42:55 AM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa | Siêu thị đồ cũ</title>
        <link href="assets/thangstyle.css" rel="stylesheet"/>
        <link href="assets/sieuthidocu.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="menu_bar block--fixed block--height-large block--width-full">
            <div class="menu__container">
                <div class="logo_container block--height-full">
                    <p class="font--size-large line--height-large">
                        <a class="a--reset" href="/">
                            <span class="login__logo">Siêu thị</span> đồ cũ
                        </a>
                    </p>
                </div>
                <div class="menu block--height-full">
                    <c:if test="${not empty sessionScope.USER}">
                        <c:if test="${sessionScope.USER.roleId == 1}">
                            <div class="menu__item menu__item--color-black menu__item--color-white--hover">
                                <div class="menu__item__title">
                                    <a class="a--reset"  href="admin.jsp">Quản lý</a> 
                                </div>
                            </div>
                        </c:if>   
                    </c:if>
                    <div class="menu__item menu__item--color-black menu__item--color-white--hover">
                        <div class="menu__item__title">
                            <a class="a--reset" href="./checkout.jsp">Thanh toán</a>
                        </div>
                    </div>
                </div>
                <div class="menu__user__container block--relative">
                    <div class="menu__user__login line--height-large">
                        <c:if test="${empty sessionScope.USER}">
                            <a class="a--reset"  href="./login.jsp">Đăng nhâp</a> 
                        </c:if>
                        <c:if test="${not empty sessionScope.USER}">
                            <span>${sessionScope.USER.username}</span> |
                            <a class="a--reset"  href="./LogOutServlet">Đăng xuất</a>    
                        </c:if>
                        <span>&nbsp;</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="block--height-large"></div>
        <div class="block--height-large"></div>
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId == 1}">
            <c:set var="product" value="${requestScope.PRODUCT}"/>
            <div class="product__detail block--width-full">
                <div class="product_detail__thumbnails">
                    <div class="product_detail__image">
                        <img id="productImage" src="${product.images.imageUrl.get(0)}">
                    </div>
                </div>
                <div  class="product_detail__info">
                    <c:if test="${not empty requestScope.ERROR}">
                        <c:out value="${requestScope.ERROR}"/>
                    </c:if>
                    <form action="./UpdateProductServlet" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="txtId" value="${product.id}"/>
                        <div class="group--input">
                            <label for="txtUsername">Tên sản phẩm</label>
                            <input type="text" name="txtName" value="${product.name}" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtUsername">Giá tiền</label>
                            <input type="number" name="txtPrice" value="${product.price}" min="500" step="500" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtUsername">Giá cũ</label>
                            <input type="number" name="txtPriceOld" value="${product.priceOld}" default="1" min="1" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtUsername">Trạng thái</label>
                            <input type="number" name="txtStatus" value="${product.status}" default="90" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtUsername">Mô tả</label>
                            <textarea name="txtDescription">${product.description}</textarea>
                        </div>
                        <div class="group--input">
                            <input class="btn btn_edit" type="submit" value="Chỉnh sửa" />
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.USER or sessionScope.USER.roleId == 3}">
            Bạn không có quyền truy cập.
        </c:if>
        <div class="block--height-large"></div>
        <footer>
            <div>
                <h2>Công ty TNHH Siêu Thị Đồ Cũ</h2>
                <p>Địa chỉ: 214, lô 8, Công viên phần mềm Quang Trung, Quận 12, thành phố Hồ Chí Minh</p>
                <p>Số điện thoại: 0981 234 345 hoặc 0985 238 392</p>
            </div>
        </footer> 
    </body>
</html>
