<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Added to cart</title>
</head>
<body>
Added ${cartEntry.getQuantity()} ${cartEntry.getProduct().getDescription()}(s) to your cart!
<br>
<br>
<a href="list">Back to product list</a><br>
<a href="../customer/cart">Go to cart</a>
</body>
</html>
