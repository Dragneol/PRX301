<%-- 
    Document   : admin
    Created on : Nov 6, 2018, 9:20:34 AM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/common.css">
        <link rel="stylesheet" type="text/css" href="/FinalProject/CSS/admin.css">
    </head>
    
    <script src="/FinalProject/JS/common.js"></script>
    <script>
        const startCrawling = () => {
            document.getElementById("admin-crawl-button").disabled = true;
            document.getElementById("admin-stop-button").disabled = false;
            document.getElementById("crawler-message").innerHTML = "Crawling";
            
            callApi({
                url: '/FinalProject/crawl?action=start',
                success: text => document.getElementById("crawler-message").innerHTML = text,
                fail: error => document.getElementById("crawler-message").innerHTML = error,
                callback: () => {
                    document.getElementById("admin-crawl-button").disabled = false;
                    document.getElementById("admin-stop-button").disabled = true;
                }
            });
        };
        
        const stopCrawling = () => {
            document.getElementById("admin-stop-button").disabled = true;
            
            callApi({
                url: '/FinalProject/crawl?action=stop',
                success: text => document.getElementById("crawler-message").innerHTML = text,
                fail: error => {
                    document.getElementById("crawler-message").innerHTML = error;
                    document.getElementById("admin-stop-button").disabled = false;
                }
            });
        };
        
        const startSchedule = () => {
            document.getElementById("admin-crawl-schedule-button").disabled = true;
            
            callApi({
                url: '/FinalProject/crawl?action=start-schedule',
                success: text => {
                    document.getElementById("schedule-message").innerHTML = text;
                    document.getElementById("admin-stop-schedule-button").disabled = false;
                },
                fail: error => {
                    document.getElementById("schedule-message").innerHTML = error;
                    document.getElementById("admin-crawl-schedule-button").disabled = false;
                }
            });
        };
        
        const stopSchedule = () => {
            document.getElementById("admin-stop-schedule-button").disabled = true;
            
            callApi({
                url: '/FinalProject/crawl?action=stop-schedule',
                success: text => {
                    document.getElementById("schedule-message").innerHTML = text;
                    document.getElementById("admin-crawl-schedule-button").disabled = false;
                },
                fail: error => {
                    document.getElementById("schedule-message").innerHTML = error;
                    document.getElementById("admin-stop-schedule-button").disabled = false;
                }
            });
        };
        
        const checkSchedule = () => {
            callApi({
                url: '/FinalProject/crawl?action=check-schedule',
                success: text => {
                    if (text.includes('In schedule')) {
                        document.getElementById("admin-crawl-schedule-button").disabled = true;
                    } else if (text.includes('Not in schedule')) {
                        document.getElementById("admin-stop-schedule-button").disabled = true;
                    }
                    document.getElementById("schedule-message").innerHTML = text;
                },
                fail: error => document.getElementById("schedule-message").innerHTML = error
            });
        };
    </script>
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
        
        <c:if test="${empty admin}">
            <form method="POST" action="login">
                <span>Username:</span>
                <input type="text" name="username" value=""/>
                <span>Password:</span>
                <input type="password" name="password" value=""/>
                <input type="submit" name="action" value="Login"/>
            </form>
        </c:if>
        <c:if test="${not empty admin}">
            <div class="admin-crawl-box">
                <div class="admin-buttons">
                    <button id="admin-crawl-button" class="admin-action-button" onclick="startCrawling()">
                        Crawl data
                    </button>
                    <button id="admin-stop-button" class="admin-stop-button" onclick="stopCrawling()" disabled="true">
                        Stop crawling
                    </button>
                </div>
                <div class="admin-message">
                    <span id="crawler-message"></span>
                </div>
            </div>
            <div class="admin-crawl-box">
                <div class="admin-buttons">
                    <button id="admin-crawl-schedule-button" class="admin-action-button" onclick="startSchedule()">
                        Start Schedule
                    </button>
                    <button id="admin-stop-schedule-button" class="admin-stop-button" onclick="stopSchedule()">
                        Stop Schedule
                    </button>
                </div>
                <div class="admin-message">
                    <span id="schedule-message"></span>
                </div>
            </div>
            <script>
                checkSchedule();
            </script>
        </c:if>
    </body>
</html>
