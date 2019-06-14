<%-- 
    Document   : department
    Created on : Oct 30, 2018, 5:56:44 PM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <c:import charEncoding="UTF-8" var="departmentsXML" url="/WEB-INF/departments.xml"/>
    <c:import charEncoding="UTF-8" var="departmentSchedulesXSL" url="/XSL/DepartmentSchedules.xsl"/>
    <x:parse xml="${departmentsXML}" var="departments"/>
    
    <c:set var="id" value="${param.id}"/>
    <x:set var="department" select="$departments//*[local-name()='Department'][*[local-name()='id']=$id]"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <x:out select="$department/*[local-name()='name']"/>
        </title>
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/common.css">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/department.css">
    </head>
    
    <script src="/FinalProject/JS/common.js"></script>
    
    <body>
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
        
        <div class="department-intro">
            <div id="department-image" class="department-image"></div>
            <div class="department-info">
                <h1><x:out select="$department/*[local-name()='name']"/></h1>
                <h2 id="department-address"></h2>
                <span><b id="department-distance">Từ vị trí của bạn:</b></span>
                <span><b id="department-rating">Rating:</b></span>
            </div>
            <div class="department-map-container">
                <div id="google-map" class="department-map"></div>
            </div>
        </div>
        <div class="container">
            <script>
                const address = `<x:out select="$department/*[local-name()='address']"/>`;
                const [lat, lng, formattedAddress] = address.split(';');
                document.getElementById("department-address").innerHTML = formattedAddress;
                
                let map = null;
                
                function initMap() {
                    const mapProp = {
                        center: new google.maps.LatLng(lat, lng),
                        zoom: 15
                    };

                    const marker = new google.maps.Marker({
                        position: new google.maps.LatLng(lat, lng),
                        title: `<x:out select="$department/*[local-name()='name']"/>`
                    });

                    map = new google.maps.Map(document.getElementById("google-map"), mapProp);

                    marker.setMap(map);
                }
            </script>
            <script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyBW1y4yWGVRgi7eAgf5__xCIZ1o8iT3gf0&callback=initMap"></script>
            <script>
                const placeService = new google.maps.places.PlacesService(map);
                placeService.textSearch({
                    location: new google.maps.LatLng(lat, lng),
                    query: `<x:out select="$department/*[local-name()='name']"/>`
                }, ((response, status) => {
                    if (status === "OK") {
                        const imageUrl = response[0].photos[0].getUrl();
                        const { rating } = response[0];
                        
                        document.getElementById("department-rating").innerHTML =
                            `Rating: <i class="fas fa-star"></i> \${rating}`;
                        
                        const image = new Image();
                        image.src = imageUrl;
                        
                        image.onload = () => {    
                            document.getElementById("department-image").style.backgroundImage = `url('\${imageUrl}')`;
                        };
                    }
                }));

                function distanceMatrixCallback(response, status) {
                    if (status === "OK") {
                        const result = response.rows[0].elements[0];
                        const { distance, duration, status } = result;
                        
                        const distanceKm = distance.value / 1000;
                        const durationMin = duration.value / 60;
                        
                        if (status === "OK") {
                            document.getElementById("department-distance").innerHTML =
                                `Từ vị trí của bạn: <i class="fas fa-location-arrow"></i> \${distanceKm.toFixed(1)} km trong vòng \${durationMin.toFixed(0)} phút`;
                        }
                    }
                }

                getLocation().then(location => {
                    const origin = new google.maps.LatLng(location.latitude, location.longitude);
                    const destination = new google.maps.LatLng(lat, lng);
                    
                    const service = new google.maps.DistanceMatrixService();
                    service.getDistanceMatrix(
                        {
                          origins: [origin],
                          destinations: [destination],
                          travelMode: 'DRIVING'
                        },
                        distanceMatrixCallback
                    );
                }).catch(error => console.error(error));
            </script>
            
            <x:transform xml="${DEPARTMENT_SCHEDULES}" xslt="${departmentSchedulesXSL}">
                <x:param name="departmentId" value="${id}"/>
                <x:param name="moviesXML" value="/WEB-INF/movies.xml"/>
            </x:transform>
        </div>
                
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
