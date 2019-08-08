<%-- 
    Document   : newjsp
    Created on : Jul 26, 2019, 10:40:00 AM
    Author     : dragn
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:import var="xml" url="WEB-INF/tomcat-users.xml"/>
    <x:parse var="doc" xml="${xml}"/>
    <x:if select="$doc/tomcat-users/user[@username=$username and password=$password]">
        <jsp:forward page="welcome.jsp"/>
    </x:if>
    <h2><font color="red">Invalid username or password!!!!</font> </h2>
</html>
