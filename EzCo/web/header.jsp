<%-- 
    Document   : header
    Created on : Jul 2, 2019, 7:28:10 PM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>EzCo</title>
        <link rel="stylesheet" href="css/slide.css">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/detail.css">
    </head>

    <body>
        <header>
            <div class="logo-left">
                <span>EzCo</span>
            </div>
            <div class="list-tag">
                <ul class="wrap-list">
                    <li class="item-list"><a href="recipe.jsp"><span>Recipe</span></a></li>
                    <li class="item-list"><a href="ingredient.jsp"><span>Ingredient</span></a></li>
                </ul>
            </div>

            <form action="MainController" method="POST">
                <div class="search-right">
                    <input class="txtSearch" type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
                    <input class="button" type="submit" value="Search" name="action" />
                </div>
            </form>
        </header>
    