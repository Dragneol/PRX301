<%-- 
    Document   : product
    Created on : Nov 1, 2018, 2:20:18 PM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sản phẩm | Siêu thị đồ cũ</title>
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
        <div class="product_detail__contain">
            <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId == 1}">
                <br/>
                <button class="btn" onclick="edit()">Chỉnh sửa thông tin</button>
                <br/>
            </c:if>
            <c:set var="docXML" value="${requestScope.PRODUCT}" />
            <c:import var="xslt" url="WEB-INF/viewxsl/products.xsl" charEncoding="UTF-8"/>
            <x:transform doc="${docXML}" xslt="${xslt}" />
        </div>
        <div class="block--height-large"></div>
        <footer>
            <div>
                <h2>Công ty TNHH Siêu Thị Đồ Cũ</h2>
                <p>Địa chỉ: 214, lô 8, Công viên phần mềm Quang Trung, Quận 12, thành phố Hồ Chí Minh</p>
                <p>Số điện thoại: 0981 234 345 hoặc 0985 238 392</p>
            </div>
        </footer>
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                let btnAddToCart = document.getElementById('btnAddToCart');
                if (btnAddToCart !== null) {
                    let productId = document.getElementById('productId').value;
                    if (checkAdded(productId)) {
                        btnAddToCart.disabled = true;
                        btnAddToCart.innerHTML = 'Đã thêm vào giỏ';
                    }
                    btnAddToCart.addEventListener('click', addToCart);
                }
            })
            function checkAdded(id) {
                let products = getCart();
                let productTmp = products.find((product) => product.id === id);
                return (productTmp === undefined) ? false : true;
            }
            function addToCart() {
                let id = document.getElementById('productId').value;
                let name = document.getElementById('productName').value;
                let price = document.getElementById('productPrice').value;
                let product = {
                    id,
                    name,
                    price,
                    quantity: 1
                }
                console.log(product);
                let products = getCart();
                if (!checkAdded(id)) {
                    products.push(product);
                    localStorage.setItem('cart', JSON.stringify(products));
                    btnAddToCart.disabled = true;
                    btnAddToCart.innerHTML = 'Đã thêm vào giỏ';
                }
            }
            function getCart() {
                let products = localStorage.getItem('cart');
                if (products != null) {
                    products = JSON.parse(products);
                } else {
                    products = new Array();
                }
                return products;
            }
            function edit() {
                let productId = document.getElementById('productId').value;
                window.location = './EditProductServlet?productId=' + productId;
            }
            function changeImage(url) {
                document.getElementById('productImage').src = url;
            }
        </script>
    </body>
</html>
