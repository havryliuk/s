<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product Page</title>
</head>
<body>
<form action="../productUpdated" method="post">
    <input type="hidden" value="${product.getId()}" name="id">
    <label>Description:
    <input value="${product.getDescription()}" name="description">
</label><br>
    <label>Price:
    <input value="${product.getPrice()}" name="price"></label><br>
<label>
    <select name="category">
        <option ${product.getCategory().toString().equalsIgnoreCase("clothes") ? "selected" : ""}>clothes</option>
        <option ${product.getCategory().toString().equalsIgnoreCase("electronics") ? "selected" : ""}>electronics</option>
        <option ${product.getCategory().toString().equalsIgnoreCase("toys") ? "selected" : ""}>toys</option>
        <option ${product.getCategory().toString().equalsIgnoreCase("other") ? "selected" : ""}>other</option>
    </select>
</label><br><br>
    <input type="submit" value="Save">
</form>
<a href="../productList">Back to product list</a>
</body>
</html>
