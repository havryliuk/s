<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<form action="../customerBlock" method="post">
    <input type="hidden" value="${customer.getId()}" name="id">
    <label>Name: ${customer.getName()}</label><br>
    <label>Blocked: ${customer.isBlocked() ? "Yes" : "No"}
    </label><br>
    <input type="submit" value=${customer.isBlocked() ? "Unblock" : "Block"}>
</form>
<a href="../customerList">Back to customer list</a>
</body>
</html>
