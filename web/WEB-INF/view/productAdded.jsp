<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product added</title>
</head>
<body>
New product added.<br><br>
${product.getDescription()}<br>Price: ${product.getPrice()}<br>Category: ${product.getCategory()}<br><br>
<a href="adminMain">Go back</a><br>
</body>
</html>
