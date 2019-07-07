<%-- 
    Document   : header
    Created on : Jul 2, 2019, 7:28:10 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <c:url value="MainController" var="repAdvance">
                        <c:param name="action" value="AdvanceSearch"/>
                    </c:url>
                    <li class="item-list"><a href="${repAdvance}"><span>Recipe</span></a></li>
                    <li class="item-list"><a href="${repAdvance}"><span>Ingredient</span></a></li>
                </ul>
            </div>

            <div class="search-right">
                <form action="MainController" method="POST" >
                    <input type="text" name="txtSearch" value="${param.txtSearch}" />
                    <input class="button" type="submit" value="Search" name="action" />
                </form>
            </div>
        </header>
