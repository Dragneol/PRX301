<%-- 
    Document   : banner
    Created on : Jul 2, 2019, 9:19:53 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<banner>
    <div class="header-slide">
        <div class="slide-wrapper">
            <div class="slide-show">
                <div class="item-slide">
                    <img src="img/ImgSlide/img_slide_1.jpg" alt="img_slide_1">
                    <div class="slide-cover1">
                        <img src="img/ImgSlide/img_slide1_cover.png" alt="img_slide1_cover">
                    </div>
                    <div class="slide-cover2">
                        <img src="img/ImgSlide/img_slide1_cover.png" alt="img_slide1_cover">
                    </div>
                    <div class="slide-cover3">
                        <img src="img/ImgSlide/img_slide1_cover2.png" alt="img_slide_cover2">
                    </div>
                    <div class="content">
                        <h1>EzCo cung cấp cho quí khách những công thức nhanh nhất</h1>
                        <span></span>
                        <p>Với sự hỗ trợ tìm kiếm thông minh dựa trên thành phần thực phẩm mong muốn
                            EzCo có thể mang lại cho quí khách những công thức có thể dễ dàng nấu với tổng thời gian ngắn nhất
                        </p>
                        <c:url value="MainController" var="repAdvance">
                            <c:param name="action" value="AdvanceSearch"/>
                        </c:url>

                        <a href="${repAdvance}">Tìm kiếm Công thức</a>
                    </div>
                </div>
                <div class="item-slide">
                    <img src="img/ImgSlide/img_slide_2.jpg" alt="img_slide_2">
                    <div class="slide-cover1">
                        <img src="img/ImgSlide/img_slide2_cover.png" alt="img_slide2_cover">
                    </div>
                    <div class="slide-cover2">
                        <img src="img/ImgSlide/img_slide2_cover.png" alt="img_slide2_cover">
                    </div>
                    <div class="slide-cover3">
                        <img src="img/ImgSlide/img_slide2_cover2.png" alt="img_slide2_cover2">
                    </div>
                    <div class="content">
                        <h1>EzCo còn cung cấp cho quí khách những thực phẩm tươi ngon</h1>
                        <span></span>
                        <p>Với nguồn thực phẩm gồm hơn 700 nguyên liệu an toàn vệ sinh thực phẩm được chọn lọc, EzCo tin rằng sẽ có thể giúp quí khách có thể mua được những
                            nguyên liệu tốt nhất để đảm bảo sức khỏe cho cả gia đình
                        </p>
                        <c:url value="MainController" var="ingAdvance">
                            <c:param name="action" value="Lookup"/>
                        </c:url>
                        <a href="${ingAdvance}">Tìm kiếm nguyên liệu</a>
                    </div>
                </div>
                <div class="item-slide">
                    <img src="img/ImgSlide/img_slide_3.jpg" alt="img_slide_3">
                    <div class="slide-cover1">
                        <img src="img/ImgSlide/img_slide3_cover.png" alt="img_slide3_cover">
                    </div>
                    <div class="slide-cover2">
                        <img src="img/ImgSlide/img_slide3_cover.png" alt="img_slide3_cover">
                    </div>
                    <div class="slide-cover3">
                        <img src="img/ImgSlide/img_slide3_cover2.png" alt="img_slid3_cover2">
                    </div>
                    <div class="content">
                        <h1>EzCo rất trân trọng những feedback của quí khách</h1>
                        <span></span>
                        <p>Để có thể hỗ trợ quí khách được tốt hơn
                            Vui lòng liên hệ với Quản trị viên theo thông tin bên dưới
                            Hotline:0374484419 
                        </p>
                        <a href="https://github.com/Dragneol">Liên hệ</a>
                    </div>
                </div>
            </div>
            <div class="slide-buttons">
                <span class="item-button" value="0"></span>
                <span class="item-button" value="1"></span>
                <span class="item-button" value="2"></span>
            </div>
        </div>
    </div>
</banner>