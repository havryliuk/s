<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<form action="cart">
    <%if (((List) request.getAttribute("entries")).size() > 0) {%>
    <table>
        <tr>
            <th>Product</th>
            <th>Qty</th>
            <th>Price</th>
        </tr>
        <c:set var="total" value="${0}"/>
        <c:forEach items="${entries}" var="i">
            <tr>
                <td>
                    <a href="product/${i.getProduct().getId()}">
                            ${i.getProduct().getDescription()}</a></td>
                <td>${i.getQuantity()}</td>
                <td>${i.getProduct().getPrice()}</td>
                <c:set var="total" value="${total + i.getQuantity() * i.getProduct().getPrice()}" />
            </tr>
        </c:forEach>
    </table>
    <br>
    <label>Total: ${total}</label><br><br>
    <input type="submit" value="Place order" formmethod="post" formaction="submitOrder"><br>
    <%} else {%>
    Your cart is empty...
    <%}%>
</form>
<a href="customerMain">Go back</a>
</body>
</html>