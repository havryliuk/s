<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<form action="../product/addToCart" method="post">
    <input type="hidden" value="${product.getId()}" name="id">
    <label>Description: ${product.getDescription()}</label><br>
    <label>Price: ${product.getPrice()}</label><br>
    <label>Category: ${product.getCategory().toString()}</label><br>
    <label>Pcs: <input value="1" name="quantity"> </label>
    <input type="submit" value="Add to cart">
</form>
<a href="list">Back to product list</a>
</body>
</html>
