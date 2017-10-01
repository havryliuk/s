<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Customer blocked</title>
</head>
<body>
<label>Customer ${customer.getName()} ${customer.isBlocked() ? "" : "un"}blocked!</label><br>
<a href="customerList">Back to customer list</a>
</body>
</html>
