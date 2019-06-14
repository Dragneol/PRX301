<%-- 
    Document   : detail.jsp
    Created on : Oct 29, 2018, 4:55:39 PM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <c:import charEncoding="UTF-8" var="moviesXML" url="/WEB-INF/movies.xml"/>
    <c:import charEncoding="UTF-8" var="departmentsXML" url="/WEB-INF/departments.xml"/>
    <x:parse xml="${moviesXML}" var="movies"/>
    <c:set var="id" value="${param.id}"/>
    <x:set var="movie" select="$movies//*[local-name()='Movie'][*[local-name()='id']=$id]"/>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <x:out select="$movie/*[local-name()='VietnameseName']"/>
        </title>
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/common.css">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/detail.css">
    </head>
    
    <script src="/FinalProject/JS/common.js"></script>
    <script>
        let currentDate = null;
        let totalScheduleList = null;
        let scheduleList = null;
        let showingNumber;
        
        const getMovieSchedule = date => {
            document.getElementById('movie-schedule').innerHTML = '<span>Đang tải...</span>';
            document.getElementById('detail-search-text').value = '';
            callApi({
                url: `/FinalProject/schedule?id=${id}&date=\${date}`,
                success: text => {
                    const parser = new DOMParser();
                    const xmlDoc = parser.parseFromString(text, "text/xml");
                    const schedules = xmlDoc.getElementsByTagName("MovieSchedule");
                    
                    getDepartments(`${departmentsXML}`).then(departments => {
                        showingNumber = 8;
                        scheduleList = Array.from(schedules).map(schedule => {
                            const departmentId = schedule.getElementsByTagName("departmentId")[0].childNodes[0].nodeValue;
                            const department = departments.find(department => department.id === departmentId);
                            let result = '';

                            if (department) {
                                result += `<tr>`;
                                result += `<td class="detail-department">
                                               <a href="/FinalProject/department?id=\${department.id}">\${department.name}</a>
                                           </td>
                                           <td class="detail-distance">
                                                \${department.distance ?
                                                    '<i class="fas fa-location-arrow"></i> ' + department.distance.toFixed(1) + ' km' :
                                                    ''
                                                }
                                           </td>
                                           <td class="detail-time-list">`;

                                const bookingUrl = schedule.getElementsByTagName("bookingUrl")[0].childNodes[0].nodeValue;

                                const timeList = schedule.getElementsByTagName("time");
                                Array.from(timeList).forEach(time => {
                                    if (time.childNodes[0]) {
                                        result += `<a href="\${bookingUrl}" target="_blank">\${time.childNodes[0].nodeValue}</a>`;
                                    }
                                });

                                result += `</td></tr>`;
                            }
                            return { result, department: department };
                        });

                        scheduleList.sort((row1, row2) => {
                            if (!row1.department || !row1.department.distance) return 1;
                            if (!row2.department || !row2.department.distance) return -1;
                            return row1.department.distance - row2.department.distance;
                        });

                        totalScheduleList = scheduleList;

                        currentDate = date;
                        renderDateSelection();
                        renderSchedule();
                    });
                },
                fail: error => {
                    console.error(error);
                    document.getElementById('movie-schedule').innerHTML =
                    `<span onclick="getMovieSchedule('\${date}')">An error occurred. Click here to reload!</span>`;
                }
            });
        };
        
        const loadMore = () => {
            showingNumber += 5;
            renderSchedule();
        };
        
        const renderSchedule = () => {
            const rows = scheduleList.slice(0, showingNumber);
            const button = scheduleList.length > showingNumber ?
                `<tr>
                     <td colspan="3">
                         <button onclick="loadMore()">Xem thêm</button>
                     </td>
                 </tr>` : '';;
            
            document.getElementById('movie-schedule').innerHTML =
                `<table class="detail-table">
                    \${rows.map(row => row.result).join('')}
                    \${button}
                </table>`;
        };
        
        const searchDepartment = () => {
            const searchValue = document.getElementById('detail-search-text').value.toLowerCase();
            scheduleList = totalScheduleList.filter(schedule => {
                if (schedule.department && schedule.department.name.toLowerCase().includes(searchValue)) {
                    return true;
                }
            });
            if (scheduleList.length === 0) {
                document.getElementById('movie-schedule').innerHTML = '<span>Không tìm thấy kết quả</span>';
            } else {
                showingNumber = 8;
                renderSchedule();
            }
        };
        
        const renderDateSelection = () => {
            const today = new Date();
            
            const tomorrow = new Date();
            tomorrow.setDate(today.getDate() + 1);
            
            const dayAfterTomorrow = new Date();
            dayAfterTomorrow.setDate(today.getDate() + 2);
            
            const listDate = [today, tomorrow, dayAfterTomorrow];
            const listTabs = listDate.map(date => {
                const dateString = formatDate(date);
                const dateDisplay = formatDateDisplay(date);
                const className = currentDate === dateString ? "detail-day-selection selected" : "detail-day-selection";

                return `<span class="\${className}" onclick="getMovieSchedule('\${dateString}')">\${dateDisplay}</span>`;
            });
            
            document.getElementById('date-select').innerHTML = `\${listTabs.join(' ')}`;
        };
        
        const renderAfterLoading = () => {
            renderDateSelection();
            
            const EnglishNameElement = document.getElementsByClassName("detail-english-name")[0];
            EnglishNameElement.innerHTML = EnglishNameElement.innerHTML.toUpperCase();
            const VietnameseNameElement = document.getElementsByClassName("detail-vietnamese-name")[0];
            VietnameseNameElement.innerHTML = VietnameseNameElement.innerHTML.toUpperCase();
        };
    </script>
    <body onload="getMovieSchedule(formatDate(new Date())); renderAfterLoading();">
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
        
        <div class="container">
            <div class="detail-movie">
                <div
                    class="detail-image"
                    style="background-image: url('<x:out select="$movie/*[local-name()='imageUrl']"/>');"
                ></div>
                
                <div class="detail-content">
                    <h1 class="detail-vietnamese-name">
                        <x:out select="$movie/*[local-name()='VietnameseName']"/>
                    </h1>
                    <h1 class="detail-english-name">
                        <x:out select="$movie/*[local-name()='EnglishName']"/>
                    </h1>
                    <span class="detail-attribute">
                        <b>Đạo diễn:</b> <x:out select="$movie/*[local-name()='director']"/>
                    </span>
                    <span class="detail-attribute">
                        <b>Diễn viên:</b> <x:out select="$movie/*[local-name()='stars']"/>
                    </span>
                    <span class="detail-attribute">
                        <b>Thời lượng:</b> <x:out select="$movie/*[local-name()='duration']"/> phút
                    </span>
                    <span class="detail-attribute">
                        <b>Thể loại:</b> <x:out select="$movie/*[local-name()='category']"/>
                    </span>
                    <span class="detail-attribute">
                        <b>Ngôn ngữ:</b> <x:out select="$movie/*[local-name()='languages']"/>
                    </span>
                    <span class="detail-attribute">
                        <b>Xếp loại:</b> <x:out select="$movie/*[local-name()='type']"/>
                    </span>
                    <span class="detail-attribute">
                        <x:out select="$movie/*[local-name()='description']"/>
                    </span>
                </div>
            </div>
            
            <div class="detail-schedule">
                <h1 class="detail-schedule-title">Lịch chiếu phim</h1>
                <div class="detail-schedule-search">
                    <input type="text" id="detail-search-text" placeholder="Tìm rạp chiếu"/>
                    <button onclick="searchDepartment()">Tìm kiếm</button>
                </div>
            </div>
            <div id="date-select" class="detail-day-selections"></div>
            <div id="movie-schedule"><span>Đang tải...</span></div>
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
