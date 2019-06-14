<%-- 
    Document   : admin
    Created on : Oct 30, 2018, 4:48:09 PM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <c:if test="${not empty sessionScope.USER and sessionScope.USER.roleId == 1}">
            <h1>Admin!</h1>
            <button class="btn" id="btnCrawl" onclick="crawl()">Crawl</button>
            <div id="txtLoad" style="display: none">Đang trích xuất dữ liệu, vui lòng đợi!</div>
            <script>

                function crawl() {
                    document.getElementById('btnCrawl').disabled = true;
                    document.getElementById('txtLoad').style.display = 'block';
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = () => {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            document.getElementById('txtLoad').style.display = 'none';
                            if (xhttp.responseXML.childNodes[0].textContent.toString() == 'done')
                                alert('Crawl done');
                            else {
                                document.getElementById('btnCrawl').disabled = false;
                                alert('Something wrong');
                            }
                        }
                    };
                    xhttp.open("GET", "./CrawlServlet", true);
                    xhttp.send();
                }
            </script>
        </c:if>
        <c:if test="${empty sessionScope.USER or sessionScope.USER.roleId == 3}">
            Bạn không có quyền truy cập.
        </c:if>
    </body>
</html>
