<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Ship</title>
</head>
<body>
    <div align="center">
        <form:form action="save" method="post" modelAttribute="ship">
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
                    <td>prodDate: </td>
                    <td><form:input path="prodDate" /></td>
                </tr>
                <tr>
                    <td>isUsed: </td>
                    <td><form:input path="isUsed" /></td>
                </tr>
                <tr>
                    <td>Speed: </td>
                    <td><form:input path="speed" /></td>
                </tr>
                <tr>
                    <td>crewSize: </td>
                    <td><form:input path="crewSize" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>
