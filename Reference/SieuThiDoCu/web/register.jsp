<%-- 
    Document   : register
    Created on : Nov 2, 2018, 12:03:58 AM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký | Siêu thị đồ cũ</title>
        <link href="assets/thangstyle.css" rel="stylesheet"/>
        <link href="assets/sieuthidocu.css" rel="stylesheet"/>
    </head>
    <body>
        <div>
            <div class="login__background"></div>
            <div class="block--absolute block--position-center login__container">
                <div>
                    <a href="/">
                        <h2><span class="login__logo">Siêu thị</span> đồ cũ</h2>
                    </a>
                    <h1>Login</h1>
                    <c:if test="${not empty requestScope.ERROR}">
                        <p style="color: red">
                            <c:out value="${requestScope.ERROR}"/>
                        </p>
                    </c:if>
                </div>
                <div class="login__form">
                    <form action="./RegisterServlet" method="POST" id="registerForm" onsubmit="checkPassword(event)" accept-charset="UTF-8">
                        <div class="group--input">
                            <label for="txtUsername">Tài khoản</label>
                            <input type="text" name="txtUsername" id="txtUsername" minlength="4" maxlength="20" autofocus required/>
                        </div>
                        <div class="group--input">
                            <label for="txtPassword">Mật khẩu</label>
                            <input type="password" name="txtPassword" id="txtPassword" pattern="^[a-zA-Z0-9,.@]{8,20}$" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtConfirmPassword">Xác nhận mật khẩu</label>
                            <input type="password" name="txtConfirmPassword" id="txtConfirmPassword" required/>
                            <p id="error" style="color:red;display: none">Mật khẩu không trùng</p>
                        </div>
                        <div class="group--input">
                            <label for="txtName">Tên</label>
                            <input type="text" name="txtName" id="txtName" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtAddress">Địa chỉ</label>
                            <input type="text" name="txtAddress" id="txtAddress" required/>
                        </div>
                        <div class="group--input">
                            <label for="txtEmail">Email</label>
                            <input type="email" name="txtEmail" id="txtEmail" />
                        </div>
                        <div class="group--input">
                            <label for="txtPhone">Số điện thoại</label>
                            <input type="tel" name="txtPhone" id="txtPhone" pattern="[0-9]{10,11}" required/>
                        </div>
                        <div class="group--input">
                            <input type="submit" value="Đăng kí" class="btn btn--position-right btn--type-success"/>
                            <div class="clearfloat"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function checkPassword(event) {
                let errorTag = document.getElementById('error');
                let password = document.getElementById('txtPassword').value;
                let confirmPassword = document.getElementById('txtConfirmPassword').value;
                errorTag.style.display = 'none';
                if (confirmPassword !== password) {
                    event.preventDefault();
                    errorTag.style.display = 'initial';
                }
            }
        </script>
    </body>
</html>
