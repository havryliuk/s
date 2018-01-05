<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product added</title>
</head>
<body>
New product created.<br><br>
${product.getDescription()}<br>Price: ${product.getPrice()}<br>Category: ${product.getCategory()}<br><br>
<a href="../admin/main">Go back</a><br>
</body>
</html>
