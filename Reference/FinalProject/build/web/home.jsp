<%-- 
    Document   : home
    Created on : Oct 3, 2018, 8:12:48 AM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <c:import charEncoding="UTF-8" var="moviesXML" url="/WEB-INF/movies.xml"/>
    <c:import charEncoding="UTF-8" var="moviesXSL" url="/XSL/MovieList.xsl"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinema</title>
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/common.css">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/home.css">
    </head>
    
    <script>
        const loadSlider = () => {
            const count = document.getElementsByClassName("movie-slide-item").length;
            
            const slider = document.getElementsByClassName("movie-slide")[0];
            slider.style.width = `\${count * 100}%`;
            let index = 1;
            
            setInterval(() => {
                if (index === 0) {
                    slider.style.transition = "none";
                } else {
                    slider.style.transition = null;
                }
                slider.style.transform = `translateX(\${-index * 100 / count}%)`;
                index++;
                if (index >= count) {
                    index = 0;
                }
            }, 8000);
        };
    </script>
    <body onload="loadSlider()">
        <c:set var="admin" value="${sessionScope.ADMIN}"/>
        <nav>
            <div>
                <div><a href="/FinalProject/">Phim đang chiếu</a></div>
                <div><a href="/FinalProject/theaters">Hệ thống rạp</a></div>
            </div>
            <c:if test="${not empty admin}">
                <div style="float: right;"><a href="/FinalProject/logout">Log out</a></div>
            </c:if>
        </nav>
        
        <x:transform xml="${moviesXML}" xslt="${moviesXSL}"/>
        
        <footer>
            <div>
                <h1>CÔNG TY CỔ PHẦN MOVIE TIME</h1>
                <p>Địa chỉ: Công viên phần mềm Quang Trung, Tân Chánh Hiệp, Quận 12</p>
                <p>Điện thoại: 039.360.7379</p>
                <p>Email: nvhungkt1997@gmail.com</p>
            </div>
            <div>
                <h1>ĐỐI TÁC</h1>
                <div class="footer-partners">
                    <div class="footer-partner-item">
                        <h2>CGV Cinemas</h2>
                        <p>CÔNG TY TNHH CJ CGV VIETNAM</p>
                        <a href="https://www.cgv.vn/"><img src="/FinalProject/IMG/logo-cgv.png"/></a>
                    </div>
                    <div class="footer-partner-item">
                        <h2>Lotte Cinema</h2>
                        <p>LOTTE CINEMA VIỆT NAM</p>
                        <a href="https://lottecinemavn.com/"><img src="/FinalProject/IMG/logo-lotte.png"/></a>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
