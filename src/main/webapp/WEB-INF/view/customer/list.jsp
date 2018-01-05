<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
<form action="list">
    <table>
        <tr>
            <th>Name</th>
            <th>Blocked</th>
        </tr>
        <c:forEach items="${customers}" var="i">
            <tr>
                <td>
                    <a href="${i.getId()}">
                            ${i.getName()}</a>
                </td>
                <td>${i.isBlocked().equals(true) ? "Yes" : "No"}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<a href="../admin/main">Go back</a>
</body>
</html>
