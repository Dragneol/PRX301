<%-- 
    Document   : header
    Created on : Jun 1, 2019, 9:43:19 AM
    Author     : dragn
--%>
<html>
    <head>
        <link rel="stylesheet" href="header_style.css">
    </head>
    <body>
        <div class="pre-header"></div>
        <div class="logo-container">
            <a href="index.jsp" class="logo-link" >B n S</a>
        </div>
        <div class="dropdown-container">
            <div class="dropdown">
                <button class="dropbtn">Shoes</button>
                <div class="dropdown-content">
                    <a href="#">Link 1</a>
                    <a href="#">Link 2</a>
                    <a href="#">Link 3</a>
                </div>
            </div>
            <div class="dropdown">
                <button class="dropbtn">Bag</button>
                <div class="dropdown-content">
                    <a href="#">Link 1</a>
                    <a href="#">Link 2</a>
                    <a href="#">Link 3</a>
                </div>
            </div>
            <div class="dropdown">
                <button class="dropbtn">
                    <a href="#" class="dropdown-link">Collection</a>
                </button>
            </div>
            <div class="dropdown">
                <button class="dropbtn">
                    <a href="#" class="dropdown-link">New trends</a>
                </button>
            </div>
        </div>
        <div class="search-container">
            <form action="#">
                <input type="text" placeholder="Search.." name="search" class="search-bar">
                <button type="submit" class="submit-button"></button>
            </form>
        </div>
    </body>
</html>