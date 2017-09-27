<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<form action="../customerBlocked" method="post">
    <input type="hidden" value="${customer.getId()}" name="id">
    <label>Name: ${customer.getName()}</label><br>
    <label>Blocked: ${customer.isBlocked().equals(true) ? "Yes" : "No"}</label><br>
    <input type="submit" ${customer.isBlocked().equals(true) ? "disabled" : ""} value="Block">
</form>
<a href="../customerList">Back to customer list</a>
</body>
</html>
