<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result search</title>
</head>
<body>
<a href="main-ships.jsp">Main page</a>
<c:forEach items="${result}" var="ship">
    <tr>
        <td>${ship.id}</td>
        <td>${ship.name}</td>
        <td>${ship.planet}</td>
        <td>${ship.ship_type}</td>
        <td>${ship.prodDate}</td>
        <td>${ship.isUsed}</td>
        <td>${ship.speed}</td>
        <td>${ship.crewSize}</td>
        <td>${ship.rating}</td>
        <td>
            <a href="/edit?id=${ship.id}">Edit</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/delete?id=${ship.id}">Delete</a>
        </td>
    </tr>
</c:forEach>
</body>
</html>
