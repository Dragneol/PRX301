<%-- 
    Document   : theater
    Created on : Oct 30, 2018, 9:45:58 AM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <c:import charEncoding="UTF-8" var="departmentsXML" url="/WEB-INF/departments.xml"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cụm rạp chiếu</title>
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/common.css">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/theaters.css">
    </head>
    <script src="/FinalProject/JS/common.js"></script>
    <script>
        let totalDepartmentList = null;
        let departmentList =  null;
        
        const loadDepartments = () => {
            getDepartments(`${departmentsXML}`).then(departments => {
                departments.sort((d1, d2) => {
                    if (!d1.distance) return 1;
                    if (!d2.distance) return -1;
                    return d1.distance - d2.distance;
                });
                
                const theaterIds = [];
                departments.forEach(department => {
                    if (theaterIds.indexOf(department.theaterId) === -1) {
                        theaterIds.push(department.theaterId);
                    }
                });
                
                departmentList = theaterIds.map(theaterId => {
                    const rows = departments.filter(department => department.theaterId === theaterId).map(department => ({
                        result:
                            `<tr>
                                <td class="theaters-department">
                                    <a href="/FinalProject/department?id=\${department.id}">\${department.name}</a>
                                </td>
                                <td class="theaters-distance">
                                    \${department.distance ?
                                        '<i class="fas fa-location-arrow"></i> ' + department.distance.toFixed(1) + ' km' :
                                        ''
                                    }
                                </td>
                                <td class="theaters-address">\${department.address}</td>
                            </tr>`,
                        department
                    }));
                    return { theaterId, rows, showingNumber: 8 };
                });
                
                totalDepartmentList = departmentList;
                
                renderDepartments();
            });
        };
        
        const loadMore = theaterId => {
            departmentList.forEach(theater => {
                if (theater.theaterId === theaterId) {
                    
                    theater.showingNumber += 5;
                }
            });
            renderDepartments();
        };
        
        const renderDepartments = () => {
            document.getElementById('theaters').innerHTML = '';
            departmentList.forEach(theater => {
                const length = theater.showingNumber;
                const rows = theater.rows.slice(0, length);
                
                if (rows.length === 0) return;
                
                const button = length < theater.rows.length ?
                    `<tr>
                         <td colspan="3">
                             <button onclick="loadMore('\${theater.theaterId}')">Xem thêm</button>
                         </td>
                     </tr>` : '';
                
                document.getElementById('theaters').innerHTML +=
                    `<table class="theaters-table">
                        \${rows.map(row => row.result).join('')}
                        \${button}
                    </table>`;
            });
            if (document.getElementById('theaters').innerHTML === '') {
                document.getElementById('theaters').innerHTML = '<span>Không tìm thấy kết quả</span>';
            }
        };
        
        const searchDepartment = () => {
            const searchValue = document.getElementById('theaters-search-text').value.toLowerCase();
            departmentList = totalDepartmentList.map(theater => {
                const rows = theater.rows.filter(row => {
                    if (row.department && row.department.name.toLowerCase().includes(searchValue)) {
                        return true;
                    }
                });
                return { theaterId: theater.theaterId, rows, showingNumber: 8 }
            });
            renderDepartments();
        };
    </script>
    <body onload="loadDepartments()">
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
            <div class="theaters-header">
                <h1>Cụm rạp chiếu</h1>
                <div class="theaters-search">
                    <input type="text" id="theaters-search-text" placeholder="Tìm rạp chiếu"/>
                    <button onclick="searchDepartment()">Tìm kiếm</button>
                </div>
            </div>
            <div id="theaters" class="theaters"></div>
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
