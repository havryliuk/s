<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit Product Page</title>
</head>
<body>
<form action="../product/update" method="post">
    <input type="hidden" value="${product.getId()}" name="id">
    <label>Description:
    <input value="${product.getDescription()}" name="description">
</label><br>
    <label>Price:
    <input value="${product.getPrice()}" name="price"></label><br>
<label>
    <select name="category">
        <option ${product.getCategory().toString().equalsIgnoreCase("clothes") ? "selected" : ""}>CLOTHES</option>
        <option ${product.getCategory().toString().equalsIgnoreCase("electronics") ? "selected" : ""}>ELECTRONICS</option>
        <option ${product.getCategory().toString().equalsIgnoreCase("toys") ? "selected" : ""}>TOYS</option>
        <option ${product.getCategory().toString().equalsIgnoreCase("other") ? "selected" : ""}>OTHER</option>
    </select>
</label><br><br>
    <input type="submit" value="Save">
</form>
<a href="../product/list">Back to product list</a>
</body>
</html>
