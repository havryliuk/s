<%@ page import="com.havryliuk.store.entity.Customer" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<% if (((Customer) request.getAttribute("customer")).isBlocked()) {%>
<form action="unblock" method="post">
    <input type="hidden" value="${customer.getId()}" name="id">
    <label>Name: ${customer.getName()}</label><br>
    <label>Blocked: Yes
    </label><br>
    <input type="submit" value="Unblock">
</form>
<% } else {%>
<form action="block" method="post">
    <input type="hidden" value="${customer.getId()}" name="id">
    <label>Name: ${customer.getName()}</label><br>
    <label>Blocked: No
    </label><br>
    <input type="submit" value="Block">
</form>
<% } %>
<a href="list">Back to customer list</a>
</body>
</html>
