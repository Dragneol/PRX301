<%-- 
    Document   : login
    Created on : Nov 1, 2018, 11:54:24 PM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập | Siêu thị đồ cũ</title>
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
                    <form action="./LoginServlet" method="POST" id="loginForm">
                        <div class="group--input">
                            <label for="txtUsername">Tài khoản</label>
                            <input type="text" name="txtUsername" id="txtUsername" minlength="4"  maxlength="20" autofocus required/>
                        </div>
                        <div class="group--input">
                            <label for="txtPassword">Mật khẩu</label>
                            <input type="password" name="txtPassword" id="txtPassword" minlength="8" maxlength="20" required/>
                        </div>
                        <div class="group--input">
                            <input type="submit" value="Đăng nhập" class="btn btn--position-right btn--type-success"/>
                            <div class="clearfloat"></div>
                        </div>
                        <div class="login__link">
                            <a href="./register.jsp" onclick="showRegisterForm()">Tạo tài khoản mới</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
