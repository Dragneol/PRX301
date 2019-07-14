<%-- 
    Document   : ingredient
    Created on : Jun 23, 2019, 9:59:24 AM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="banner.jsp"/>
<script type="text/javascript">
    var xmlHttp;
    var PAGE_SIZE = 6;
    var num = 0;
    var parser = new DOMParser();
    function getXmlHttpObject() {
        var xmlHttp = null;
        if (window.XMLHttpRequest) {
            // code for modern browsers
            xmlHttp = new XMLHttpRequest();
        } else {
            // code for old IE browsers
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlHttp;
    }

    function search() {
        num++;
        xmlHttp = getXmlHttpObject();
        if (xmlHttp === null) {
            alert("Your browser not support AJAX");
            return;
        }
        var url = "MainController?action=IngController&txtSearch=" + document.getElementById("txtSearch").value + "&page=" + PAGE_SIZE + "&num=" + num;
        xmlHttp.open("GET", url, true);
        xmlHttp.addEventListener("load", handleResponse);
        xmlHttp.addEventListener("error", handleError);
        xmlHttp.addEventListener("abort", handleAbort);
        xmlHttp.send();
    }
</script>
<content>
    <h1>Tìm kiếm nguyên liệu dựa trên từ khóa</h1>
    <div class="search-center">
        <input class="txtSearch" type="text" id="txtSearch" value="${param.txtSearch}" /><br/>
        <input class="button" type="button" onclick="search()" value="Search"/>
    </div>
    <div id="list-ingredients"></div>
    <div class="load-button" id="load-button-ingredients" >Next Page</div>
</content>
<script type="text/javascript">
    var loadButton = document.getElementById("load-button-ingredients");
    loadButton.addEventListener('click', search);
    function handleResponse() {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            var tmp = xmlHttp.responseText;
            var domTree = parser.parseFromString(tmp, "application/xml");
            displayResult(domTree);
        }
    }

    function handleError(evt) {
        console.log("An error occurred while Searching.");
    }

    function handleAbort(evt) {
        console.log("The searching has been canceled by the user.");
    }

    function displayResult(docXML) {
        var lists = document.getElementById("list-ingredients");
        var ingredients = docXML.getElementsByTagName('ingredient');
        var showedElement = '';
        for (var i = 0; i < ingredients.length; i++) {
            var recipe = ingredients[i];
            var id = recipe.getElementsByTagName('id')[0].textContent;
            var name = recipe.getElementsByTagName('name')[0].textContent;
            var price = recipe.getElementsByTagName('price')[0].textContent;
            var link = recipe.getElementsByTagName('link')[0].textContent;
            var image = recipe.getElementsByTagName('image')[0].textContent;
            var description = recipe.getElementsByTagName('description')[0].textContent;
            showedElement += `<div class="product-item">
        <div class="image-product">
            <img src="\${image}" alt="Hình ảnh của \${name}" >
            <div class="info">
                <p>\${name}</p>
            </div>
        </div>
        <div class="content-product">
            <div class="content">
               <h3>Giá: \${price} đồng</h3>
                <p>\${description}</p>
            </div>
            <div>
                <a class="button" href="\${link}">Tới chỗ mua</a>
            </div>
        </div>
    </div>`
        }

        if (ingredients.length < PAGE_SIZE) {
            loadButton.disabled = true;
        } else {
            loadButton.disabled = false;
        }

        if (ingredients.length === 0) {
            showedElement = '<p>Không còn kết quả có thể tìm thấy.Vui lòng thử với từ khóa khác</p>';
            num = 0;
        }
        lists.innerHTML = showedElement;
    }
</script>
<script src="js/slide.js"></script>
<jsp:include page="footer.jsp"/>