<%--
  Created by IntelliJ IDEA.
  User: a4241
  Date: 31.05.2020
  Time: 21:34
  To change this template use File | Settings | File Templates.
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Ship Manager</h2>
    <%--<form method="get" action="search">
        &lt;%&ndash;<input type="text" name="keyword" /> &nbsp;&ndash;%&gt;
        <input type="submit" value="Search" />
    </form>--%>

    <h3><a href="/search">Search</a></h3>

    <h3><a href="/new">New Ship</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Planet</th>
            <th>ship_type</th>
            <th>prodDate</th>
            <th>isUsed</th>
            <th>Speed</th>
            <th>crewSize</th>
            <th>rating</th>
            <th>Action</th>
        </tr>

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
        <c:if test="${10 > 8}">
        <p>True</p>
        </c:if>


    </table>
</div>
</body>
</html>
