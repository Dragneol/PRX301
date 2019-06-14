<%-- 
    Document   : checkout
    Created on : Nov 1, 2018, 7:09:39 PM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thanh toán | Siêu thị đồ cũ</title>
        <link href="assets/thangstyle.css" rel="stylesheet"/>
        <link href="assets/sieuthidocu.css" rel="stylesheet"/>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
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
        <div class="cart__contain">
            <div class="cart">
                <div id="cart">

                </div>
                <div>
                    <h3>Tổng tiền: <span id="totalCost"></span></h3>
                </div>
                <div><button class="btn btn_edit btnCheckout" id="btnCheckout" onclick="showCheckoutForm()">Thanh toán</button></div>
            </div>
        </div>
        <div class="form__checkout__contain" id="formContain" style="display: none">
            <div class="background" style="background-color: #0004" onclick="closePop()"></div>
            <div class="form__checkout">
                <h2>Thông tin khách hàng</h2>
                <p style="color:red" id="errorInfo"></p>
                <form method="post" id="form">
                    <div class="group--input">
                        <label for="txtAddress">Địa chỉ</label>
                        <input type="text" name="txtAddress" id="txtAddress" value="${user.address}" required/>
                    </div>
                    <div class="group--input">
                        <label for="txtEmail">Email</label>
                        <input type="email" name="txtEmail" id="txtEmail" value="${user.email}"/>
                    </div>
                    <div class="group--input">
                        <label for="txtPhone">Số điện thoại</label>
                        <input type="tel" name="txtPhone" id="txtPhone" pattern="[0-9]{10,11}" value="${user.phone}" required/>
                    </div>
                    <div class="group--input">
                        <input class="btn btn_edit" type="submit" value="Thanh toán" />
                        <input class="btn" type="button" value="Đóng"  onclick="closePop()" />
                    </div>
                </form>
            </div>
        </div>
        <div class="block--height-large"></div>
        <footer class="absolute">
            <div>
                <h2>Công ty TNHH Siêu Thị Đồ Cũ</h2>
                <p>Địa chỉ: 214, lô 8, Công viên phần mềm Quang Trung, Quận 12, thành phố Hồ Chí Minh</p>
                <p>Số điện thoại: 0981 234 345 hoặc 0985 238 392</p>
            </div>
        </footer>
        <script>
            var cartProduct;
            var totalCost;
            document.addEventListener("DOMContentLoaded", () => {
                cartProduct = getCart();
                displayCart(cartProduct);
                calcCost();
                let form = document.getElementById('form');
                form.onsubmit = checkout;
            });
            function getCart() {
                let products = localStorage.getItem('cart');
                if (products != null) {
                    products = JSON.parse(products);
                } else {
                    products = new Array();
                }
                return products;
            }
            function displayCart(products) {
                let cartTag = document.getElementById('cart');
                if (products.length == 0) {
                    cartTag.innerHTML = "<h1>Giỏ hàng hiện đang trống</h1>";
                    document.getElementById('btnCheckout').disabled = true;
                    return;
                }
                let s = '';
                for (var i = 0; i < products.length; i++) {
                    let product = products[i];
                    s += `<div class="cart__item">
                            <div class="cart__product__info">
                                <h3 class="product__name text--ellipsis">\${product.name}</h3>
                                <p class="text--ellipsis">
                                    <span class="info__title">Giá: </span> 
                                    <span class="cart__product__price">\${product.price} đ</span>
                                </p>
                            </div>
                            <div class="cart__product__control">
                                <br/>
                                <span class="info__title">Số lượng: </span>
                                <input type="number" id="quantity\${product.id}" name="quantity" min="1" step="1" value="\${product.quantity}" onchange="calcCost()"/>
                                <br/>
                                <br/>
                                <button class="btn btn_addToCart" onclick="remove('\${product.id}')">Bỏ</button>
                            </div>
                        </div>`
                }
                cartTag.innerHTML = s;
            }
            function calcCost() {
                totalCost = 0;
                for (var i = 0; i < cartProduct.length; i++) {
                    let product = cartProduct[i];
                    product.quantity = document.getElementById('quantity' + product.id).value;
                    totalCost += product.price * product.quantity;
                }
                document.getElementById('totalCost').innerHTML = displayCurrency(totalCost, 'đ');
            }
            function remove(id) {
                cartProduct = cartProduct.filter(product => product.id !== id);
                localStorage.setItem('cart', JSON.stringify(cartProduct));
                displayCart(cartProduct);
                calcCost();
            }

            function getProductInfo() {
                let str = '';
                for (var i = 0; i < cartProduct.length; i++) {
                    let product = cartProduct[i];
                    let quantity = document.getElementById('quantity' + product.id).value;
                    str += product.id + '-' + quantity + ' ';
                }
                return str.trim();
            }

            function showCheckoutForm() {
                document.getElementById('formContain').style.display = 'block';
            }

            function checkout(event) {
                event.preventDefault();
                let xhttp = new XMLHttpRequest();
                let params = 'txtAddress=' + document.getElementById('txtAddress').value
                        + '&txtEmail=' + document.getElementById('txtEmail').value
                        + '&txtPhone=' + document.getElementById('txtPhone').value
                        + '&products=' + getProductInfo()
                        + '&totalCost=' + totalCost;
                xhttp.open('POST', './CheckOutServlet', true);
                xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

                xhttp.onreadystatechange = () => {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        let resultXML = xhttp.responseXML;
                        let result = resultXML.getElementsByTagName('result')[0].textContent;
                        if (result === 'true') {
                            cartProduct = []
                            localStorage.removeItem('cart');
                            window.location = './success.jsp';
                        } else {
                            document.getElementById('errorInfo').innerHTML = 'Đã xảy ra lỗi, vui lòng thử lại!'
                        }
                    }
                }
                xhttp.send(params);
            }
            function closePop() {
                document.getElementById('errorInfo').innerHTML = ''
                document.getElementById('formContain').style.display = 'none';
            }
            function displayCurrency(money, unit) {
                if (money == 1) {
                    return 'Liên hệ';
                }
                money += '';
                money = money.replace(/(\d{3})$/g, ',$1');
                while (/(\d{4}),/g.test(money)) {
                    money = money.replace(/(\d{3}),/, ',$1,');
                }
                return money + ' ' + unit;
            }
        </script>
    </body>
</html>
