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
    var count = 0;
    var cells = [];
    var xmlHttp;
    var xmlDOM;
    var new_XMLDOM;
    var tmp = "${requestScope.INFO}";
    var parser = new DOMParser();
    xmlDOM = parser.parseFromString(tmp, "application/xml");
</script>
<content>
    <h1>Tìm kiếm nguyên liệu dựa trên từ khóa</h1>
    <div class="search-center">
        <form action="MainController" method="POST">
            <input class="txtSearch" type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <input class="button" type="submit" value="Lookup" name="action" />
        </form>
    </div>
</content>
<script src="js/slide.js"></script>
<jsp:include page="footer.jsp"/>