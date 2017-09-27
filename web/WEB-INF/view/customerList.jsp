<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
<form action="customerList">
    <table>
        <tr>
            <th>Name</th>
            <th>Blocked</th>
        </tr>
        <c:forEach items="${customers}" var="i">
            <tr>
                <td>
                    <a href="customer/${i.getId()}">
                            ${i.getName()}</a>
                </td>
                <td>${i.isBlocked().equals(true) ? "Yes" : "No"}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<a href="adminMain">Go back</a>
</body>
</html>
