<%-- 
    Document   : searchBanking
    Created on : Jun 19, 2019, 1:00:29 PM
    Author     : dragn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Searching Banking Page</title>
    </head>
    <body>
        <x:set var="currentAcc" select="$doc//*[@username=$username]"/>
        <font color="red">
        <h1>Hello <x:out select="$currentAcc/fullname"/>!</h1>
        </font><br/>
        Your balance : <x:out select="$currentAcc/@balance"/>
        <h1>Transaction List</h1>
        <form action="searchBanking.jsp" method="POST">
            From Date <input type="text" name="txtFromDate" 
                             value="${param.txtFromDate}" /> (yyyy/mm/dd)<br/>
            To Date <input type="text" name="txtToDate" 
                           value="${param.txtToDate}" /> (yyyy/mm/dd)<br/>
            <input type="submit" value="List" />
        </form>
        <c:set var="fromDate" value="${param.txtFromDate}"/>
        <c:set var="toDate" value="${param.txtToDate}"/>
        <c:set var="fromDate" value="${fn:replace(fromDate, '/','')}"/>
        <c:set var="toDate" value="${fn:replace(toDate, '/','')}"/>

        <x:set var="transaction" select="$currentAcc//transaction[translate(date, '/','')>= $fromDate and translate(date,'/','') <= $toDate]"/>

        <x:if select="$transaction">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody>
                    <x:forEach var="trans" select="$transaction" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td><x:out select="$trans/date"/></td>
                            <td><x:out select="$trans/amount"/></td>
                            <td>
                                <x:choose>
                                    <x:when select="$trans[type=0]">Withdraw</x:when>
                                    <x:when select="$trans[type=1]">Deposit</x:when>
                                    <x:when select="$trans[type=2]">Transfer</x:when>
                                    <x:otherwise>Your account is hacked</x:otherwise>
                                </x:choose>
                        </tr>
                    </x:forEach>
                </tbody>
            </table>
        </x:if>
        <x:if select="$transaction != null">
            <h3>No record</h3>
        </x:if>
    </body>
</html>
