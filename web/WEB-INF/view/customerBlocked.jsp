<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Customer blocked</title>
</head>
<body>
<label>Customer ${customer.get().getName()} ${customer.get().isBlocked() ? "" : "un"}blocked!</label>
<br><br>
<a href="customerList">Back to customer list</a>
</body>
</html>
