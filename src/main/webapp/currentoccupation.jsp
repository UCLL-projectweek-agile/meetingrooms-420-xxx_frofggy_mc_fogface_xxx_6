<%-- 
    Document   : currentoccupation
    Created on : Dec 19, 2017, 1:22:17 PM
    Author     : Daan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="appointment" items="${appointments}">
            <p>${appointments}</p>
        </c:forEach>
    </body>
</html>
