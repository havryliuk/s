<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Product saved</title>
</head>
<body>
Product info saved.<br><br>
${product.getDescription()}<br>Price: ${product.getPrice()}<br>Category: ${product.getCategory()}<br><br>
<a href="list">Go to product list</a>
</body>
</html>
