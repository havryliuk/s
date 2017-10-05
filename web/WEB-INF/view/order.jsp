<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Order</title>
</head>
<body>
<form action="order">
    Order ID: ${order.getId()} Paid: ${order.isPaid() ? "Yes" : "No"}<br><br>
    <table>
        <tr>
            <th>Product</th>
            <th>Qty</th>
            <th>Price</th>
        </tr>
<c:set var="total" value="${0}"/>
<c:forEach items="${order.getProducts()}" var="i">
    <tr>
        <td>${i.getKey().getDescription()}</td>
        <td>${i.getValue()}</td>
        <td>${i.getKey().getPrice()}</td>
        <c:set var="total" value="${total + i.getValue() * i.getKey().getPrice()}" />
    </tr>
</c:forEach>
    </table>
    <br>
    <label>Total: ${total}</label><br><br>
    <input type="submit" ${order.isPaid() ? "hidden" : ""} value="Pay this order"
           formmethod="post" formaction="../payOrder/${order.getId()}">
</form>
<a href="../orders">Back to orders list</a>
</body>
</html>