<%-- 
    Document   : index
    Created on : Oct 29, 2018, 2:28:43 PM
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sieu thi do cu</title>
        <link href="assets/thangstyle.css" rel="stylesheet"/>
        <link href="assets/sieuthidocu.css" rel="stylesheet"/>
        <script>
            var choosenCategoryId;
            var page;
            var numInPage;
            var isSearch;
            var filter;
            document.addEventListener('DOMContentLoaded', () => {
                page = 0;
                numInPage = 20;
                choosenCategoryId = null;
                isSearch = false;
                filter = document.getElementById('sltFilter').value;
                loadData(page, numInPage);
            })
        </script>
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
        <div class="main_contain">
            <div class="block--height-large"></div>
            <div class="main">
                <div class="nar_bar">
                    <div class="search__contain">
                        <input class="search__input" type="text" id="txtSearch" name="txtSearch" onkeyup="pressEnter(event)"/>
                        <button class="btn search__button btn--borderradius-right" onclick="clickSearch()">Search</button>
                    </div>
                    <div class="search__contain">
                        <select class="filter btn--borderradius" id="sltFilter" name="sltFilter" onchange="changeFilter()">
                            <optgroup label="Đánh giá">
                                <option value="1">Đáng mua</option>
                            </optgroup>
                            <optgroup label="Giá">
                                <option value="2">Giá thấp</option>
                                <option value="3">Giá cao</option>
                            </optgroup>
                            <optgroup label="Độ mới">
                                <option value="4">Đồ mới</option>
                                <option value="5">Đồ cũ</option>
                            </optgroup>
                        </select>
                    </div>
                    <c:import var="xslt" url="WEB-INF/viewxsl/category.xsl" charEncoding="UTF-8"/>
                    <c:set var="categoryXML" value="${requestScope.CATEGORY}" />
                    <x:transform doc="${categoryXML}" xslt="${xslt}" />
                </div>
                <div class="product_show">
                    <div class="product__list" id="product_list">
                    </div>
                    <div class="paging_contain">
                        <button class="btn btn--borderradius-left" id="btnPre" value="Previous" onclick="prePage()" disabled="true">&lt; Trước</button>
                        <button class="btn btn--borderradius-right" id="btnNext" value="Next" onclick="nextPage()"/>Sau &gt;</button>
                    </div>
                </div>

            </div>
        </div>
        <script>

            function loadData(loadPage, loadNum) {
                let xhttp = new XMLHttpRequest();
                xhttp.open('GET', './ProductsServlet?page=' + loadPage + '&num=' + loadNum + "&filter=" + filter, true);
                xhttp.onreadystatechange = () => {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        displayResult(xhttp.responseXML);
                    }
                }
                xhttp.send();
            }
            function changeFilter() {
                filter = document.getElementById('sltFilter').value;
                if (isSearch) {
                    clickSearch();
                } else if(choosenCategoryId != null) {
                    clickCategory(choosenCategoryId);
                } else {
                    loadData(page, numInPage);
                }
            }
            function clickCategory(categoryId) {
                resetPaging();
                setCategoryActive(categoryId);
                getProductCategory(categoryId);
            }
            function clickSearch() {
                resetPaging();
                search();
            }
            function pressEnter(event) {
                if (event.code == 'Enter') {
                    clickSearch();
                }
            }
            function getProductCategory(categoryId) {
                isSearch = false;
                choosenCategoryId = categoryId;
                let xhttp = new XMLHttpRequest();
                xhttp.open('GET', './ProductCategoryServlet?categoryId=' + categoryId + '&page=' + page + '&num=' + numInPage + '&filter=' + filter, true);
                xhttp.onreadystatechange = () => {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        displayResult(xhttp.responseXML);
                    }
                }
                xhttp.send();
            }
            function search() {
                isSearch = true;
                let txtSearch = document.getElementById('txtSearch').value;
                let xhttp = new XMLHttpRequest();
                let params = 'txtSearch=' + txtSearch + '&categoryId=' + choosenCategoryId;

                xhttp.open("POST", './SearchServlet?page=' + page + '&num=' + numInPage + '&filter=' + filter, true);
                xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xhttp.onreadystatechange = () => {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        displayResult(xhttp.responseXML);
                    }
                };
                xhttp.send(params);
            }
            function displayResult(docXML) {
                let productsTag = document.getElementById('product_list');
                let products = docXML.getElementsByTagName('product');
                let s = '';
                for (let i = 0; i < products.length; i++) {
                    let product = products[i];
                    let id = product.getElementsByTagName('id')[0].textContent;
                    let name = product.getElementsByTagName('name')[0].textContent;
                    let price = product.getElementsByTagName('price')[0].textContent;
                    price = displayCurrency(price, 'đ');
                    let status = product.getElementsByTagName('status')[0].textContent;
                    status = Number(status);
                    let imgUrl = product.getElementsByTagName('imageUrl')[0].textContent;

                    s += `<a class="a--reset" href="./ShowProductServlet?productId=\${id}">
                            <div class="product__card">
                                <div class="product__image">
                                    <div class="background" style="background-image: url('\${imgUrl}')"></div>
                                </div>
                                <div class="product__info">
                                    <p class="product__name text--ellipsis">\${name}</p>
                                    <p class="text--ellipsis">
                                        <span class="info__title">Giá: </span> 
                                        <span class="product__price">\${price}</span>
                                    </p>
                                    <p class="text--ellipsis">
                                        <span class="info__title">Tình trạng: </span>
                                        <span class="product__status">\${status}%</span>
                                    </p>
                                </div>
                            </div>
                        </a>`
                }
                if (products.length < numInPage) {
                    btnNext.disabled = true;
                } else {
                    btnNext.disabled = false;
                }
                if (products.length == 0) {
                    s = '<p style="color:#fff">Không tìm thấy kết quả nào</p>';
                }
                productsTag.innerHTML = s;
            }
            function displayCurrency(money, unit) {
                if (money == 1) {
                    return 'Liên hệ';
                }
                money = money.replace(/(\d{3})$/g, ',$1');
                while (/(\d{4}),/g.test(money)) {
                    money = money.replace(/(\d{3}),/, ',$1,');
                }
                return money + ' ' + unit;
            }
            function prePage() {
                let prePage = page - 1;
                let btnPre = document.getElementById("btnPre");
                let btnNext = document.getElementById("btnNext");
                btnNext.disabled = false;
                if (prePage == 0) {
                    btnPre.disabled = true;
                } else {
                    btnPre.disabled = false;
                }
                if (prePage == -1)
                    return;
                loadPaging(prePage);
            }
            function nextPage() {
                let productsTag = document.getElementById('product_list');
                let nextPage = page + 1;
                let btnPre = document.getElementById("btnPre");
                let btnNext = document.getElementById("btnNext");
                btnPre.disabled = false;
                loadPaging(nextPage);
            }

            function loadPaging(nextPage) {
                page = nextPage;
                if (isSearch) {
                    search();
                } else if (choosenCategoryId != null) {
                    getProductCategory(choosenCategoryId);
                } else {
                    loadData(page, numInPage);
                }
            }
            function resetPaging() {
                page = 0;
                let btnPre = document.getElementById("btnPre");
                btnPre.disabled = true;
            }
            function setCategoryActive(categoryId) {
                let categoryTag = document.getElementById('category' + categoryId);
                let categories = document.getElementsByClassName('category');
                for (var i = 0; i < categories.length; i++) {
                    categories[i].classList.remove('active')
                }
                categoryTag.classList.add('active');
            }
        </script>
    </body>
</html>
