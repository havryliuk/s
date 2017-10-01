<%@ page import="com.havryliuk.dao.UserType" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Your orders</title>
</head>
<body>
<form action="orders">
    <%if (((List) request.getAttribute("orders")).size() > 0) {%>
    <table>
        <tr>
            <th>Order Id</th>
            <th>Paid</th>
        </tr>
        <c:forEach items="${orders}" var="i">
            <tr>
                <td>
                    <a href="order/${i.getId()}">
                    ${i.getId()}</a></td>
                <td>${i.isPaid() ? "Yes" : "No"}</td>
            </tr>
        </c:forEach>
    </table>
    <%} else {%>
    You don't have any orders...
    <%}%>
</form>
<%if (session.getAttribute("userType").equals((UserType.ADMIN).toString())) {%>
<a href="adminMain">Go back</a>
<%} else if (session.getAttribute("userType").equals((UserType.CUSTOMER).toString())) {%>
<a href="customerMain">Go back</a>
<%}%>
</body>
</html>
