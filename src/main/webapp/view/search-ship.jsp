<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Search Ship by:</title>
</head>
<body>
<a href="http://localhost:8080/">Main page</a>
<div align="center">
    <form:form action="search-ship-by-param" method="get" modelAttribute="shipDtoForSearch">
        <table border="0" cellpadding="5">
            <tr>
                <td>Name: </td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Planet: </td>
                <td><form:input path="planet" /></td>
            </tr>
            <tr>
                <td>ship_type: </td>
                <td><form:input path="ship_type" /></td>
            </tr>
            <tr>
                <td>minRating: </td>
                <td><form:input path="minRating" /></td>
            </tr>
            <tr>
                <td>maxRating: </td>
                <td><form:input path="maxRating" /></td>
            </tr>
            <tr>
                <td>minCrewSize: </td>
                <td><form:input path="minCrewSize" /></td>
            </tr>
            <tr>
                <td>maxCrewSize: </td>
                <td><form:input path="maxCrewSize" /></td>
            </tr>
            <tr>
                <td>minSpeed: </td>
                <td><form:input path="minSpeed" /></td>
            </tr>
            <tr>
                <td>maxSpeed: </td>
                <td><form:input path="maxSpeed" /></td>
            </tr>
            <tr>
                <td>after date: </td>
                <td><form:input path="after" /></td>
            </tr>
            <tr>
                <td>before date: </td>
                <td><form:input path="before" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Search"></td>
            </tr>
        </table>
    </form:form>

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


    </table>
</div>
</body>
</html>
